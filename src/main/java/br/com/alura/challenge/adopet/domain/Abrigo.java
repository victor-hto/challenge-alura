package br.com.alura.challenge.adopet.domain;

import br.com.alura.challenge.adopet.domain.dto.DadosCadastroAbrigoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "abrigo")
public class Abrigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String endereco;

    public Abrigo(DadosCadastroAbrigoDto dados) {
        this.nome = dados.nome();
        this.endereco = dados.endereco();
        this.telefone = dados.telefone();
    }
}
