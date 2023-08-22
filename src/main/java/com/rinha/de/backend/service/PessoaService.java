package com.rinha.de.backend.service;


import com.rinha.de.backend.convert.RequestConverter;
import com.rinha.de.backend.convert.ResponseConverter;
import com.rinha.de.backend.dto.RequestDto;
import com.rinha.de.backend.dto.ResponseDto;
import com.rinha.de.backend.exceptions.ValidationException;
import com.rinha.de.backend.model.Pessoa;
import com.rinha.de.backend.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;

    public int count(){
        return repository.countAllById();
    }
    public ResponseDto getPessoas(String id){
        var pessoa = repository.findById(id);
        if(pessoa.isPresent()) {
            return ResponseConverter.convert(pessoa.get());
        }
        return null;
    }
    public Pessoa createPessoa(RequestDto requestDto) {
        var pessoa = RequestConverter.convert(requestDto);
        var uuid = UUID.randomUUID().toString();
        pessoa.setId(uuid);

        if (repository.findAllByApelido(pessoa.getApelido()).isPresent()){
            throw new ValidationException("Apelido ja cadastrado");
        }
        log.info("salvando pessoa ...");
        return repository.save(pessoa);
    }

    public List<ResponseDto> getByTermo(String termo){
        if(termo.isBlank()){
            throw new ValidationException("Termo é Obrigatorio");
        }
        var pessoas = repository.searchByTermo(termo,
                PageRequest.of(0, 50)).getContent();
        List<ResponseDto> responseDto = new ArrayList<>();
        pessoas.forEach(p -> responseDto.add(ResponseConverter.convert(p)));
        return responseDto;
    }
}
