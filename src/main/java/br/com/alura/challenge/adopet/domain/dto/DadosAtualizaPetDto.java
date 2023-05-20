package br.com.alura.challenge.adopet.domain.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaPetDto(
        @NotNull
        Long id,
        @NotNull
        Long abrigo_id,
        String nome,
        String descricao,
        String idade,
        String endereco,
        String imagem,
        Boolean adotado) {

}
