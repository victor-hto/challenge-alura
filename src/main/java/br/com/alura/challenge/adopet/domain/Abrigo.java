package br.com.alura.challenge.adopet.domain;

import br.com.alura.challenge.adopet.domain.dto.DadosAtualizaAbrigoDto;
import br.com.alura.challenge.adopet.domain.dto.DadosCadastroAbrigoDto;
import br.com.alura.challenge.adopet.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "abrigo")
public class Abrigo extends Usuario {

    private String nome;
    private String telefone;
    private String endereco;

    public Abrigo(DadosCadastroAbrigoDto dados) {
        this.nome = dados.nome();
        this.endereco = dados.endereco();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.senha = dados.senha();
    }

    public void atualizarDados(DadosAtualizaAbrigoDto dados) {
        this.nome = dados.nome() != null ? dados.nome() : this.nome;
        this.endereco = dados.endereco() != null ? dados.endereco() : this.endereco;
        this.telefone = dados.telefone() != null ? dados.telefone() : this.telefone;
        this.email = dados.email() != null ? dados.email() : this.email;
        this.senha = dados.senha() != null ? dados.senha() : this.senha;
    }
}
