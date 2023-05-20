package br.com.alura.challenge.adopet.controller;

import br.com.alura.challenge.adopet.domain.dto.DadosCadastroAdocaoDto;
import br.com.alura.challenge.adopet.domain.dto.DadosListagemAdocaoDto;
import br.com.alura.challenge.adopet.services.AdotarPetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adocao")
public class AdocaoController {

    @Autowired
    private AdotarPetService adotarPetService;

    @PostMapping
    @Transactional
    public ResponseEntity adotarPet(@RequestBody @Valid DadosCadastroAdocaoDto dadosCadastro) {
        DadosListagemAdocaoDto dadosListagem = adotarPetService.controleAdotarPet(dadosCadastro);
        return ResponseEntity.ok(dadosListagem);
    }

    @DeleteMapping("{id}")
    public ResponseEntity cancelarAdocaoPet(@PathVariable Long id) {
        adotarPetService.controleCancelarAdocaoPet(id);
        return ResponseEntity.ok("Dados removidos com sucesso!");
    }
}
