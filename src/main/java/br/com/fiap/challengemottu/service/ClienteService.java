package br.com.fiap.challengemottu.service;

import br.com.fiap.challengemottu.dto.ClienteRequest;
import br.com.fiap.challengemottu.dto.ClienteResponse;
import br.com.fiap.challengemottu.mapper.ClienteMapper;
import br.com.fiap.challengemottu.model.Cliente;
import br.com.fiap.challengemottu.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper = new ClienteMapper();

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponse save(ClienteRequest clienteRequest) {
        if (clienteRequest.idade() < 18) {
            throw new IllegalArgumentException("O cliente deve ter mais de 18 anos.");
        }
        Cliente cliente = clienteMapper.requestToCliente(clienteRequest);
        return clienteMapper.clienteToResponse(clienteRepository.save(cliente));
    }

    public List<ClienteResponse> findAll() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::clienteToResponse)
                .collect(Collectors.toList());
    }

    public Cliente findClienteById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElse(null);
    }

    public ClienteResponse findById(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::clienteToResponse)
                .orElse(null);
    }

    public ClienteResponse update(ClienteRequest clienteRequest, Long id) {
        if (clienteRequest.idade() < 18) {
            throw new IllegalArgumentException("O cliente deve ter mais de 18 anos.");
        }

        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            cliente.setNome(clienteRequest.nome());
            cliente.setEmail(clienteRequest.email());
            cliente.setCpf(clienteRequest.cpf());
            cliente.setTelefone(clienteRequest.telefone());
            cliente.setIdade(clienteRequest.idade());

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
