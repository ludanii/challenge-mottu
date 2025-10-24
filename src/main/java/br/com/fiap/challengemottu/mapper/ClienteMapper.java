package br.com.fiap.challengemottu.mapper;

import java.util.List;

import br.com.fiap.challengemottu.dto.ClienteRequest;
import br.com.fiap.challengemottu.dto.ClienteResponse;
import br.com.fiap.challengemottu.model.Cliente;
import br.com.fiap.challengemottu.model.Moto;

public class ClienteMapper {

    public Cliente requestToCliente(ClienteRequest clienteRequest) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequest.nome());
        cliente.setEmail(clienteRequest.email());
        cliente.setCpf(clienteRequest.cpf());
        cliente.setTelefone(clienteRequest.telefone());
        cliente.setIdade(clienteRequest.idade());
        return cliente;
    }

    public ClienteResponse clienteToResponse(Cliente cliente) {
        List<Long> motosIds = cliente.getMotos()
                .stream()
                .map(Moto::getId)
                .toList();
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getIdade(),
                motosIds);
    }
}
