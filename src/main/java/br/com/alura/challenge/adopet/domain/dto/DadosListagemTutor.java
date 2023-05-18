package br.com.alura.challenge.adopet.domain.dto;

import br.com.alura.challenge.adopet.domain.Tutor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosListagemTutor(String nome, String email) {
    public DadosListagemTutor(Tutor tutor) {
        this(tutor.getNome(), tutor.getEmail());
    }
}
