package com.rinha.de.backend.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private String id;
    private String apelido;
    private String nome;
    private String nascimento;
    private List<String> stack;
}
