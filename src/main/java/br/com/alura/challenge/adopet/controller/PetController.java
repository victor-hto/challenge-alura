package br.com.alura.challenge.adopet.controller;

import br.com.alura.challenge.adopet.domain.Pet;
import br.com.alura.challenge.adopet.domain.dto.DadosAtualizaPetDto;
import br.com.alura.challenge.adopet.domain.dto.DadosCadastroPetDto;
import br.com.alura.challenge.adopet.domain.dto.DadosListagemPetDto;
import br.com.alura.challenge.adopet.infra.ValidacaoException;
import br.com.alura.challenge.adopet.repository.AbrigoRepository;
import br.com.alura.challenge.adopet.repository.PetRepository;
import br.com.alura.challenge.adopet.services.AtualizarPetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Autowired
    private AtualizarPetService atualizarPetService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPetDto dados) {
        var abrigo = abrigoRepository.findById(dados.abrigo_id());
        if (abrigo.isEmpty()) {
            throw new ValidacaoException("O abrigo informado não existe.");
        }
        var pet = new Pet(dados,abrigo.get());
        petRepository.save(pet);
        return ResponseEntity.ok(new DadosListagemPetDto(pet));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizaPetDto dados) {
        var tutor = atualizarPetService.atualizarPet(dados);
        return ResponseEntity.ok(tutor);
    }

    @GetMapping
    public ResponseEntity listar() {
        var lista = petRepository.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.ok("Nenhum item encontrado");
        }
        return ResponseEntity.ok(lista.stream().map(DadosListagemPetDto::new).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        var pet = petRepository.findById(id);
        if (pet.isEmpty()) {
            return ResponseEntity.ok("Nenhum item encontrado");
        }
        return ResponseEntity.ok(pet.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!petRepository.existsById(id)) {
            return ResponseEntity.ok("Nenhum item encontrado");
        }
        petRepository.deleteById(id);
        return ResponseEntity.ok("Dados apagados com sucesso!");
    }
}
