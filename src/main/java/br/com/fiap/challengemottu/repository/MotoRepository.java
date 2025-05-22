package br.com.fiap.challengemottu.repository;

import br.com.fiap.challengemottu.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {
}
