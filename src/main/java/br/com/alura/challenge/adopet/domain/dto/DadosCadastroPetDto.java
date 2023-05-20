package br.com.alura.challenge.adopet.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPetDto(
        @NotNull
        Long abrigo_id,
        @NotBlank
        String nome,
        @NotBlank
        String descricao,
        @NotBlank
        String idade,
        @NotBlank
        String endereco,
        @NotBlank
        String imagem,
        @NotNull
        Boolean adotado
) {
}
