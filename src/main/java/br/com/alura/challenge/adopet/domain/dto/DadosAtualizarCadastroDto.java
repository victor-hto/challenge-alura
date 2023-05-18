package br.com.alura.challenge.adopet.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarCadastroDto(
        @NotNull
        Long id,
        String nome,
        @Email
        String email,
        String senha,
        String confirmacaoSenha
) {
}
