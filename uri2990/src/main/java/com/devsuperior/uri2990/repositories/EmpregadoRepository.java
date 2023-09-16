package com.devsuperior.uri2990.repositories;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2990.entities.Empregado;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {

    @Query(nativeQuery = true, value = "SELECT e.cpf, e.enome, d.dnome FROM empregados e\n" +
            "INNER JOIN departamentos d ON d.dnumero = e.dnumero\n" +
            "WHERE NOT EXISTS ( \n" +
            "\tSELECT * FROM trabalha WHERE cpf_emp = e.cpf\n" +
            ") ORDER BY e.cpf")
    List<EmpregadoDeptProjection> listEmployeeNoPojects();

   

}
