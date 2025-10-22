package br.com.fiap.challengemottu.repository;

import br.com.fiap.challengemottu.model.Funcionario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    @Query("SELECT f FROM Funcionario f LEFT JOIN FETCH f.patio")
    List<Funcionario> findAllWithPatio();

    Optional<Funcionario> findByUsuario(String usuario);
}
