package br.com.alura.challenge.adopet.domain.dto;

import br.com.alura.challenge.adopet.domain.Pet;

public record DadosListagemPetDto(Long id, Long abrigo_id, String nome, String descricao, String idade, String endereco,
                                  String imagem, Boolean adotado) {
    public DadosListagemPetDto(Pet pet) {
        this(pet.getId(), pet.getAbrigo().getId(), pet.getNome(), pet.getDescricao(), pet.getIdade(), pet.getEndereco(), pet.getImagem(), pet.getAdotado());
    }
}
