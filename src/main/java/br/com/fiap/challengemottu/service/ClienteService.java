package br.com.fiap.challengemottu.service;

import br.com.fiap.challengemottu.dto.ClienteRequest;
import br.com.fiap.challengemottu.dto.ClienteResponse;
import br.com.fiap.challengemottu.exception.RecursoNaoEncontradoException;
import br.com.fiap.challengemottu.exception.RegraDeNegocioVioladaException;
import br.com.fiap.challengemottu.mapper.ClienteMapper;
import br.com.fiap.challengemottu.model.Cliente;
import br.com.fiap.challengemottu.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper = new ClienteMapper();

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    private void validarDadosCliente(ClienteRequest clienteRequest, Long clienteId) {
        if (clienteRequest.idade() < 18) {
            throw new RegraDeNegocioVioladaException("O cliente deve ter mais de 18 anos.");
        }
        if (clienteRepository.existsByCpfAndIdNot(clienteRequest.cpf(), clienteId)) {
            throw new RegraDeNegocioVioladaException("Já existe um cliente com este CPF.");
        }
        if (clienteRepository.existsByEmailAndIdNot(clienteRequest.email(), clienteId)) {
            throw new RegraDeNegocioVioladaException("Já existe um cliente com este e-mail.");
        }
    }

    public ClienteResponse save(ClienteRequest clienteRequest) {
        validarDadosCliente(clienteRequest, null);
        Cliente cliente = clienteMapper.requestToCliente(clienteRequest);
        return clienteMapper.clienteToResponse(clienteRepository.save(cliente));
    }

    public List<ClienteResponse> findAll() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::clienteToResponse)
                .toList();
    }

    public ClienteResponse findById(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::clienteToResponse)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com o ID: " + id));
    }

    public ClienteResponse update(ClienteRequest clienteRequest, Long id) {
        validarDadosCliente(clienteRequest, id);
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com o ID: " + id));

        cliente.setNome(clienteRequest.nome());
        cliente.setEmail(clienteRequest.email());
        cliente.setCpf(clienteRequest.cpf());
        cliente.setTelefone(clienteRequest.telefone());
        cliente.setIdade(clienteRequest.idade());

        return clienteMapper.clienteToResponse(clienteRepository.save(cliente));
    }

    public void delete(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Cliente não encontrado com o ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
