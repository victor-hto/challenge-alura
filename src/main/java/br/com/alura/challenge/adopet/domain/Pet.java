package br.com.alura.challenge.adopet.domain;

import br.com.alura.challenge.adopet.domain.dto.DadosAtualizaPetDto;
import br.com.alura.challenge.adopet.domain.dto.DadosCadastroPetDto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "nome", "descricao", "idade"})
@NoArgsConstructor

@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private String idade;
    private String endereco;
    private String imagem;
    private Boolean adotado;

    @ManyToOne
    @JoinColumn(name = "abrigo_id")
    private Abrigo abrigo;


    public Pet(DadosCadastroPetDto dados, Abrigo abrigo) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.idade = dados.idade();
        this.endereco = dados.endereco();
        this.imagem = dados.imagem();
        this.adotado = dados.adotado();
        this.abrigo = abrigo;
    }

    public void atualizarDados(DadosAtualizaPetDto dados, Abrigo abrigo) {
        this.nome = dados.nome() != null ? dados.nome() : this.nome;
        this.descricao = dados.descricao() != null ? dados.descricao() : this.descricao;
        this.idade = dados.idade() != null ? dados.idade() : this.idade;
        this.endereco = dados.endereco() != null ? dados.endereco() : this.endereco;
        this.imagem = dados.imagem() != null ? dados.imagem() : this.imagem;
        this.adotado = dados.adotado() != null ? dados.adotado() : this.adotado;
        this.abrigo = abrigo != null ? abrigo : this.abrigo;
    }

}
