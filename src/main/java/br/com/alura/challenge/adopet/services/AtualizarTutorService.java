package br.com.alura.challenge.adopet.services;

import br.com.alura.challenge.adopet.domain.Tutor;
import br.com.alura.challenge.adopet.domain.dto.DadosAtualizarCadastroDto;
import br.com.alura.challenge.adopet.domain.dto.DadosListagemTutor;
import br.com.alura.challenge.adopet.infra.exception.ValidacaoException;
import br.com.alura.challenge.adopet.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtualizarTutorService {

    @Autowired
    private TutorRepository tutorRepository;

    public DadosListagemTutor atualizarTutor(DadosAtualizarCadastroDto dados) {
        if (dados.senha() != null || dados.confirmacaoSenha() != null) {
            if (!dados.senha().equals(dados.confirmacaoSenha())) {
                throw new ValidacaoException("As senhas informadas não são iguais!");
            }
        }

        Tutor tutor = tutorRepository.findById(dados.id()).orElseThrow(() -> new ValidacaoException("ID do tutor não existe!"));
        tutor.atualizarDados(dados);

        return new DadosListagemTutor(tutor);
    }
}
