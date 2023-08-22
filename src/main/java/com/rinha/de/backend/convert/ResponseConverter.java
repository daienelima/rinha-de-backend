package com.rinha.de.backend.convert;

import com.rinha.de.backend.dto.ResponseDto;
import com.rinha.de.backend.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class ResponseConverter {
    public static ResponseDto convert (Pessoa pessoa){
        return ResponseDto.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .nascimento(pessoa.getNascimento())
                .apelido(pessoa.getApelido())
                .stack(getStack(pessoa.getStack()))
                .build();
    }

    private static List<String> getStack(String stacks){
        List<String> stringList = new ArrayList<>();

        String stackSplit [] = stacks.split(",");
        for(String s: stackSplit){
            stringList.add(s.trim());
        }
        return stringList;
    }
}
