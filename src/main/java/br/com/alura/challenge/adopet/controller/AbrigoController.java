package br.com.alura.challenge.adopet.controller;

import br.com.alura.challenge.adopet.domain.Abrigo;
import br.com.alura.challenge.adopet.domain.dto.DadosCadastroAbrigoDto;
import br.com.alura.challenge.adopet.domain.dto.DadosListagemAbrigoDto;
import br.com.alura.challenge.adopet.repository.AbrigoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoRepository abrigoRepository;

    @PostMapping
    private ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroAbrigoDto dados) {
        var abrigo = abrigoRepository.save(new Abrigo(dados));
        return ResponseEntity.ok(new DadosListagemAbrigoDto(abrigo.getId(), abrigo.getNome(), abrigo.getTelefone(), abrigo.getEndereco()));
    }
}
