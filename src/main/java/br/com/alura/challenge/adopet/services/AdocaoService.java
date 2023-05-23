package br.com.alura.challenge.adopet.services;

import br.com.alura.challenge.adopet.domain.Adocao;
import br.com.alura.challenge.adopet.domain.dto.DadosCadastroAdocaoDto;
import br.com.alura.challenge.adopet.domain.dto.DadosListagemAdocaoDto;
import br.com.alura.challenge.adopet.infra.exception.ValidacaoException;
import br.com.alura.challenge.adopet.repository.AdocaoRepository;
import br.com.alura.challenge.adopet.repository.PetRepository;
import br.com.alura.challenge.adopet.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdocaoService {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public DadosListagemAdocaoDto controleAdotarPet(DadosCadastroAdocaoDto dados) {
        var pet = petRepository.findById(dados.pet_id()).orElseThrow(() -> new ValidacaoException("Pet não encontrado!"));
        if (pet.getAdotado()) {
            throw new ValidacaoException("Pet já foi adotado!");
        }

        var tutor = tutorRepository.findById(dados.tutor_id()).orElseThrow(() -> new ValidacaoException("Tutor não encontrado!"));

        var adocao = new Adocao(pet, tutor);

        pet.setAdotado(true);
        adocaoRepository.save(adocao);

        return new DadosListagemAdocaoDto(adocao);
    }

    public void controleCancelarAdocaoPet(Long id) {
        var adocao = adocaoRepository.findById(id).orElseThrow(() -> new ValidacaoException("Adoção não encontrada!"));
        var pet = adocao.getPet();
        pet.setAdotado(false);
        adocaoRepository.delete(adocao);
    }
}
