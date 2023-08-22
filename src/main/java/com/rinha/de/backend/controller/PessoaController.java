package com.rinha.de.backend;

import com.rinha.de.backend.dto.RequestDto;
import com.rinha.de.backend.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService service;
    @GetMapping("/pessoas")
    public ResponseEntity<?> getPessoasByTermo(@RequestParam String t){
        var response = service.getByTermo(t);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<?> getPessoas(@PathVariable(value = "id") String id){
        var response = service.getPessoas(id);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @PostMapping("/pessoas")
    public ResponseEntity<?> createPessoa(@Valid @RequestBody RequestDto pessoa){
        var response = service.createPessoa(pessoa);
        URI location = URI.create(String.format("/pessoas/%s", response.getId()));
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/contagem-pessoas")
    public ResponseEntity<?> count(){
        var response = service.count();
        return ResponseEntity.ok(String.format("Inseridas ate o momento %s", response));
    }

}
