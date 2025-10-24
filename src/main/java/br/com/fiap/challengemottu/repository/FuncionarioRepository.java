package br.com.fiap.challengemottu.repository;

import br.com.fiap.challengemottu.model.Funcionario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByUsuario(String usuario);
    boolean existsByUsuarioAndIdNot(String usuario, Long id);
    boolean existsByEmailAndIdNot(String email, Long id);
}
