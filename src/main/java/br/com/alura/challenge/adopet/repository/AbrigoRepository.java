package br.com.alura.challenge.adopet.repository;

import br.com.alura.challenge.adopet.domain.Abrigo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {
}
