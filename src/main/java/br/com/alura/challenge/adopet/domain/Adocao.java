package br.com.alura.challenge.adopet.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "adocoes")
public class Adocao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    @OneToOne(fetch = FetchType.LAZY)
    private Pet pet;
    @ManyToOne(fetch = FetchType.LAZY)
    private Tutor tutor;

    public Adocao(Pet pet, Tutor tutor) {
        this.pet = pet;
        this.tutor = tutor;
        this.data = LocalDate.now();
    }
}
