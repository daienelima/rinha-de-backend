package com.rinha.de.backend.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pessoa {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "apelido", length = 32)
    private String apelido;

    @Column(name = "nome")
    private String nome;

    @Column(name = "nascimento")
    private String nascimento;

    @Column(name = "stack")
    private String stack;
}
