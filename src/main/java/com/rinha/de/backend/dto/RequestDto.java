package com.rinha.de.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
    private String id;
    @NotBlank(message = "Apelido nao pode ser vazio ou nulo")
    @Size(min = 1, max = 32)
    private String apelido;

    @Size(min = 1, max = 100)
    @NotBlank(message = "Nome nao pode ser vazio ou nulo")
    private String nome;

    @NotBlank(message = "Nascimento nao pode ser vazio ou nulo")
    private String nascimento;

    @Size(max = 32)
    private ArrayList<String> stack;
}
