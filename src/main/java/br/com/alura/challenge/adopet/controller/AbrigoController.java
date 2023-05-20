package br.com.alura.challenge.adopet.controller;

import br.com.alura.challenge.adopet.domain.Abrigo;
import br.com.alura.challenge.adopet.domain.dto.DadosAtualizaAbrigoDto;
import br.com.alura.challenge.adopet.domain.dto.DadosCadastroAbrigoDto;
import br.com.alura.challenge.adopet.domain.dto.DadosListagemAbrigoDto;
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
        var abrigo = abrigoRepository.save(new Abrigo(dados));
        return ResponseEntity.ok(abrigo);
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizaAbrigoDto dados) {
        if (!abrigoRepository.existsById(dados.id())) {
            return ResponseEntity.ok("ID do abrigo não existe!");
        }

        var abrigo = abrigoRepository.getReferenceById(dados.id());
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

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable Long id) {
        var abrigo = abrigoRepository.findById(id);
        if(abrigo.isEmpty()){
            return ResponseEntity.ok("Nenhum abrigo encontrado");
        }

        return ResponseEntity.ok(abrigo);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if(!abrigoRepository.existsById(id)){
            return ResponseEntity.ok("Nenhum abrigo encontrado");
        }
        abrigoRepository.deleteById(id);
        return ResponseEntity.ok("Abrigo apagado com sucesso!");
    }
}
