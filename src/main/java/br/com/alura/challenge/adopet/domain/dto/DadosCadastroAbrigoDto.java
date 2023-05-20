package br.com.alura.challenge.adopet.domain.dto;

import jakarta.validation.constraints.NotEmpty;

public record DadosCadastroAbrigoDto(
        @NotEmpty
        String nome,
        @NotEmpty
        String telefone,
        @NotEmpty
        String endereco) {
}
