package br.com.alura.challenge.adopet.repository;

import br.com.alura.challenge.adopet.domain.Adocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdocaoRepository extends JpaRepository<Adocao, Long> {
}
