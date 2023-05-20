package br.com.alura.challenge.adopet.domain.dto;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroAdocaoDto(
        @NotNull
        Long pet_id,
        @NotNull
        Long tutor_id) {
}
