package br.com.alura.challenge.adopet.domain.dto;

import br.com.alura.challenge.adopet.domain.Abrigo;

public record DadosListagemAbrigoDto(Long id, String nome, String telefone, String endereco) {

    public DadosListagemAbrigoDto(Abrigo abrigo){
        this(abrigo.getId(), abrigo.getNome(), abrigo.getTelefone(), abrigo.getEndereco());
    }
}
