package br.com.fiap.challengemottu.repository;

import br.com.fiap.challengemottu.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
