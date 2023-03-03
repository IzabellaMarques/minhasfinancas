package src.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.minhasfinancas.model.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);
}
