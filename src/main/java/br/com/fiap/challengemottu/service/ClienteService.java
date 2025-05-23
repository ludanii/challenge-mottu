package br.com.fiap.challengemottu.service;

import br.com.fiap.challengemottu.dto.ClienteRequest;
import br.com.fiap.challengemottu.dto.ClienteResponse;
import br.com.fiap.challengemottu.mapper.ClienteMapper;
import br.com.fiap.challengemottu.model.Cliente;
import br.com.fiap.challengemottu.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper = new ClienteMapper();

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponse save(ClienteRequest clienteRequest) {
        return clienteMapper.clienteToResponse(clienteRepository.save(clienteMapper.requestToCliente(clienteRequest)));
    }

    public Page<ClienteResponse> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable).map(clienteMapper::clienteToResponse);
    }

    public Cliente findClienteById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElse(null);
    }

    public ClienteResponse findById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(clienteMapper::clienteToResponse).orElse(null);
    }

    public ClienteResponse update(ClienteRequest clienteRequest, Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            cliente.setNome(clienteRequest.nome());
            cliente.setEmail(clienteRequest.email());
            cliente.setCpf(clienteRequest.cpf());
            cliente.setTelefone(clienteRequest.telefone());
            cliente.setDataNascimento(clienteRequest.dataNascimento());

            Cliente clienteAtualizado = clienteRepository.save(cliente);
            return clienteMapper.clienteToResponse(clienteAtualizado);
        }

        return null;
    }

    public boolean delete(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            clienteRepository.delete(cliente.get());
            return true;
        }
        return false;
    }
}
