package br.com.fiap.challengemottu.repository;

import br.com.fiap.challengemottu.model.Patio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatioRepository extends JpaRepository<Patio, Long> {
  @Query("SELECT p FROM Patio p LEFT JOIN FETCH p.funcionarios")
  List<Patio> findAllWithFuncionarios();
}
