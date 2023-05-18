package br.com.alura.challenge.adopet.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosAtualizarCadastro(
        @NotBlank 
        Long id,
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email) {
}
