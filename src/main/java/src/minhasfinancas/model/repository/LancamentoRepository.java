package src.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.minhasfinancas.model.entity.Usuario;

public interface LancamentoRepository extends JpaRepository<Usuario, Long> {
}
