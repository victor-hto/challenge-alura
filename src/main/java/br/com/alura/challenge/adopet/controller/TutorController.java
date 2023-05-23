package br.com.alura.challenge.adopet.controller;

import br.com.alura.challenge.adopet.domain.Tutor;
import br.com.alura.challenge.adopet.domain.dto.DadosAtualizarCadastroDto;
import br.com.alura.challenge.adopet.domain.dto.DadosCadastroTutorDto;
import br.com.alura.challenge.adopet.domain.dto.DadosListagemTutor;
import br.com.alura.challenge.adopet.infra.exception.ValidacaoException;
import br.com.alura.challenge.adopet.repository.TutorRepository;
import br.com.alura.challenge.adopet.services.AtualizarTutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

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
            throw new ValidacaoException("As senhas informadas não são iguais!");
        } else {
            tutorRepository.save(new Tutor(dados));
            return ResponseEntity.ok(dados);
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarCadastroDto dados) {
        var tutor = atualizarTutorService.atualizarTutor(dados);
        return ResponseEntity.ok(tutor);
    }

    @GetMapping
    public ResponseEntity listar() {
        var lista = tutorRepository.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.ok("Nenhum item encontrado");
        }

        return ResponseEntity.ok(lista.stream().map(DadosListagemTutor::new).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        var tutor = tutorRepository.findById(id);
        if (tutor.isEmpty()) {
            return ResponseEntity.ok("Nenhum tutor encontrado");
        }

        return ResponseEntity.ok(tutor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!tutorRepository.existsById(id)) {
            return ResponseEntity.ok("Nenhum tutor encontrado");
        }
        tutorRepository.deleteById(id);
        return ResponseEntity.ok("Tutor apagado com sucesso!");
    }
}
