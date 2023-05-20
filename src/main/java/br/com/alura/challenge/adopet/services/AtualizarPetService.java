package br.com.alura.challenge.adopet.services;

import br.com.alura.challenge.adopet.domain.Abrigo;
import br.com.alura.challenge.adopet.domain.Pet;
import br.com.alura.challenge.adopet.domain.dto.DadosAtualizaPetDto;
import br.com.alura.challenge.adopet.domain.dto.DadosListagemPetDto;
import br.com.alura.challenge.adopet.repository.AbrigoRepository;
import br.com.alura.challenge.adopet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtualizarPetService {

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Autowired
    private PetRepository petRepository;

    public DadosListagemPetDto atualizarPet(DadosAtualizaPetDto dados) {
        if (!abrigoRepository.existsById(dados.abrigo_id())) {
            throw new RuntimeException("ID do abrigo não existe!");
        }

        if (!petRepository.existsById(dados.id())) {
            throw new RuntimeException("ID do pet não existe!");
        }

        Abrigo abrigo = abrigoRepository.getReferenceById(dados.abrigo_id());
        Pet pet = petRepository.getReferenceById(dados.id());

        pet.atualizarDados(dados,abrigo);

        return new DadosListagemPetDto(pet);
    }
}
