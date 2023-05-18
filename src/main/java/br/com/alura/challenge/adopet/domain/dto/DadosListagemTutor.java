package br.com.alura.challenge.adopet.domain.dto;

import br.com.alura.challenge.adopet.domain.Tutor;

public record DadosListagemTutor(Long id, String nome, String email) {
    public DadosListagemTutor(Tutor tutor) {
        this(tutor.getId(), tutor.getNome(), tutor.getEmail());
    }
}
