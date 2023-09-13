package com.devsuperior.uri2611.repositories;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.entities.Movie;
import com.devsuperior.uri2611.projections.MovieMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository  extends JpaRepository<Movie, Long> {

    @Query(nativeQuery = true, value = "SELECT m.id, m.name FROM movies m\n" +
             "INNER JOIN  genres g ON m.id_genres = g.id\n" +
             "WHERE g.description = :genreName")
    List<MovieMinProjection> searchGenre(String genreName);

    @Query(value = "SELECT new com.devsuperior.uri2611.dto.MovieMinDTO(obj.id, obj.name) FROM Movie obj\n" +
            "WHERE obj.genre.description = :genreName")
    List<MovieMinDTO> searchGenreJPQL(String genreName);
}
