package br.com.fiap.challengemottu.repository;

import br.com.fiap.challengemottu.model.Patio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatioRepository extends JpaRepository<Patio, Long> {
}
