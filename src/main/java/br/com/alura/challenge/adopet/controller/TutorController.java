package br.com.alura.challenge.adopet.controller;

import br.com.alura.challenge.adopet.domain.Tutor;
import br.com.alura.challenge.adopet.domain.dto.DadosAtualizarCadastroDto;
import br.com.alura.challenge.adopet.domain.dto.DadosCadastroTutorDto;
import br.com.alura.challenge.adopet.repository.TutorRepository;
import br.com.alura.challenge.adopet.services.AtualizarTutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private AtualizarTutorService atualizarTutorService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTutorDto dados) {
        if (!dados.senha().equals(dados.confirmacaoSenha())) {
            return ResponseEntity.badRequest().body("As senhas informadas não são iguais!");
        } else {
            tutorRepository.save(new Tutor(dados));
            return ResponseEntity.ok(dados);
        }
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarCadastroDto dados) {
        var tutor = atualizarTutorService.atualizarTutor(dados);
        return ResponseEntity.ok(tutor);
    }
}
