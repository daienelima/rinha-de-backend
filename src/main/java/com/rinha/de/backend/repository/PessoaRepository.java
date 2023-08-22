package com.rinha.de.backend.repository;

import com.rinha.de.backend.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, String> {

    Optional<Pessoa> findAllByApelido(String apelido);

    @Query(value = "select count(*) from pessoa", nativeQuery = true)
    Integer countAllById();

    @Query(value = "select * from pessoa p where p.stack ilike %:termo% or " +
            "p.apelido ilike %:termo% or " +
            "p.nome ilike %:termo%", nativeQuery = true)
    Page<Pessoa> searchByTermo(@Param("termo") String termo, Pageable pageable);
}
