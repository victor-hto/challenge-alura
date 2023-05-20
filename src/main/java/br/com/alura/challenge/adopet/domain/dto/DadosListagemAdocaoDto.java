package br.com.alura.challenge.adopet.domain.dto;

import br.com.alura.challenge.adopet.domain.Adocao;

import java.time.LocalDate;

public record DadosListagemAdocaoDto(Long id, Long pet_id, Long tutor_id, LocalDate data) {
    public DadosListagemAdocaoDto(Adocao adocao) {
        this(adocao.getId(), adocao.getPet().getId(), adocao.getTutor().getId(), adocao.getData());
    }
}
