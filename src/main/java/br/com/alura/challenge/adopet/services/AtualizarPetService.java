package br.com.alura.challenge.adopet.services;

import br.com.alura.challenge.adopet.domain.Abrigo;
import br.com.alura.challenge.adopet.domain.Pet;
import br.com.alura.challenge.adopet.domain.dto.DadosAtualizaPetDto;
import br.com.alura.challenge.adopet.domain.dto.DadosListagemPetDto;
import br.com.alura.challenge.adopet.infra.exception.ValidacaoException;
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
        Abrigo abrigo = abrigoRepository.findById(dados.abrigo_id()).orElseThrow(() -> new ValidacaoException("ID do abrigo não existe!!"));
        Pet pet = petRepository.findById(dados.id()).orElseThrow(() -> new ValidacaoException("ID do pet não existe!!"));

        pet.atualizarDados(dados, abrigo);

        return new DadosListagemPetDto(pet);
    }
}
