package br.com.fiap.challengemottu.mapper;

import br.com.fiap.challengemottu.dto.ClienteRequest;
import br.com.fiap.challengemottu.dto.ClienteResponse;
import br.com.fiap.challengemottu.model.Cliente;

public class ClienteMapper {

    public Cliente requestToCliente(ClienteRequest clienteRequest) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequest.nome());
        cliente.setEmail(clienteRequest.email());
        cliente.setCpf(clienteRequest.cpf());
        cliente.setTelefone(clienteRequest.telefone());
        cliente.setDataNascimento(clienteRequest.dataNascimento());
        return cliente;
    }

    public ClienteResponse clienteToResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getIdCliente(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getDataNascimento());
    }
}
