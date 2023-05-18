package br.com.alura.challenge.adopet.services;

import br.com.alura.challenge.adopet.domain.Tutor;
import br.com.alura.challenge.adopet.domain.dto.DadosAtualizarCadastroDto;
import br.com.alura.challenge.adopet.domain.dto.DadosListagemTutor;
import br.com.alura.challenge.adopet.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtualizarTutorService {

    @Autowired
    private TutorRepository tutorRepository;

    public DadosListagemTutor atualizarTutor(DadosAtualizarCadastroDto dados) {
        if (!tutorRepository.existsById(dados.id())) {
            throw new RuntimeException("ID do tutor não existe!");
        }

        Tutor tutor = tutorRepository.getReferenceById(dados.id());
        tutor.setNome(dados.nome());
        tutor.setEmail(dados.email());

        tutorRepository.save(tutor);

        return new DadosListagemTutor(tutor);
    }
}
