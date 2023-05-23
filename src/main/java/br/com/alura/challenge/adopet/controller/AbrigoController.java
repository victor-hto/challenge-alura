package br.com.alura.challenge.adopet.controller;

import br.com.alura.challenge.adopet.domain.Abrigo;
import br.com.alura.challenge.adopet.domain.dto.DadosAtualizaAbrigoDto;
import br.com.alura.challenge.adopet.domain.dto.DadosCadastroAbrigoDto;
import br.com.alura.challenge.adopet.domain.dto.DadosListagemAbrigoDto;
import br.com.alura.challenge.adopet.infra.exception.ValidacaoException;
import br.com.alura.challenge.adopet.repository.AbrigoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoRepository abrigoRepository;

    @PostMapping
    private ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroAbrigoDto dados) {
        if (!dados.senha().equals(dados.confirmaSenha())) {
            throw new ValidacaoException("As senhas informadas não são iguais!");
        } else {
            var abrigo = abrigoRepository.save(new Abrigo(dados));
            return ResponseEntity.ok(new DadosListagemAbrigoDto(abrigo));
        }
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizaAbrigoDto dados) {
        Abrigo abrigo = abrigoRepository.findById(dados.id()).orElseThrow(() -> new ValidacaoException("ID do abrigo não existe!!"));
        abrigo.atualizarDados(dados);

        return ResponseEntity.ok(new DadosListagemAbrigoDto(abrigo));
    }

    @GetMapping
    private ResponseEntity listar() {
        var abrigos = abrigoRepository.findAll();
        if (abrigos.isEmpty()) {
            return ResponseEntity.ok("Nenhum registro encontrado");
        }
        return ResponseEntity.ok(abrigos.stream().map(DadosListagemAbrigoDto::new).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        Abrigo abrigo = abrigoRepository.findById(id).orElseThrow(() -> new ValidacaoException("ID do abrigo não existe!!"));
        return ResponseEntity.ok(new DadosListagemAbrigoDto(abrigo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if(!abrigoRepository.existsById(id)){
            return ResponseEntity.ok("Nenhum abrigo encontrado");
        }
        abrigoRepository.deleteById(id);
        return ResponseEntity.ok("Abrigo apagado com sucesso!");
    }
}
