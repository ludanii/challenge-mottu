package br.com.fiap.challengemottu.repository;

import br.com.fiap.challengemottu.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  boolean existsByCpfAndIdNot(String cpf, Long id);
  boolean existsByEmailAndIdNot(String email, Long id);
  boolean existsByTelefoneAndIdNot(String telefone, Long id);
}
