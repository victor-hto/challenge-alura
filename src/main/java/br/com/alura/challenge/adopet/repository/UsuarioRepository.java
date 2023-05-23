package br.com.alura.challenge.adopet.repository;

import br.com.alura.challenge.adopet.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    UserDetails findByEmail(String email);
}
