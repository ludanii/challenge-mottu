package br.com.fiap.challengemottu.service;

import br.com.fiap.challengemottu.dto.FuncionarioRequest;
import br.com.fiap.challengemottu.dto.FuncionarioResponse;
import br.com.fiap.challengemottu.exception.CredenciaisInvalidasException;
import br.com.fiap.challengemottu.exception.RecursoNaoEncontradoException;
import br.com.fiap.challengemottu.mapper.FuncionarioMapper;
import br.com.fiap.challengemottu.model.Funcionario;
import br.com.fiap.challengemottu.model.Patio;
import br.com.fiap.challengemottu.repository.FuncionarioRepository;
import br.com.fiap.challengemottu.repository.PatioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private final PatioRepository patioRepository;
    private final FuncionarioMapper funcionarioMapper = new FuncionarioMapper();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository, PatioRepository patioRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.patioRepository = patioRepository;
    }

    public FuncionarioResponse save(FuncionarioRequest funcionarioRequest) {
        Patio patio = patioRepository
        .findById(funcionarioRequest.patio())
        .orElseThrow(() -> new RecursoNaoEncontradoException("Pátio não encontrado"));
        Funcionario funcionario = funcionarioMapper.requestToFuncionario(funcionarioRequest, patio);
        funcionario.setSenha(passwordEncoder.encode(funcionarioRequest.senha()));
        return funcionarioMapper.funcionarioToResponse(funcionarioRepository.save(funcionario));
    }

    public List<FuncionarioResponse> findAll() {
        return funcionarioRepository.findAllWithPatio()
                .stream()
                .map(funcionarioMapper::funcionarioToResponse)
                .toList();
    }

    public FuncionarioResponse findById(Long id) {
        return funcionarioRepository.findById(id)
                .map(funcionarioMapper::funcionarioToResponse)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionário não encontrado com o ID: " + id));
    }

    public FuncionarioResponse update(FuncionarioRequest funcionarioRequest, Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionário não encontrado com o ID: " + id));

        Patio patio = patioRepository.findById(funcionarioRequest.patio())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pátio não encontrado"));

        funcionario.setUsuario(funcionarioRequest.usuario());
        funcionario.setNome(funcionarioRequest.nome());
        funcionario.setEmail(funcionarioRequest.email());
        funcionario.setSenha(passwordEncoder.encode(funcionarioRequest.senha()));
        funcionario.setTipo(funcionarioRequest.tipo());
        funcionario.setPatio(patio);

        return funcionarioMapper.funcionarioToResponse(funcionarioRepository.save(funcionario));

    }

    public void delete(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Funcionário não encontrado com o ID: " + id);
        }
        funcionarioRepository.deleteById(id);
    }

    public FuncionarioResponse login(String usuario, String senha, String tipo) {
        Funcionario funcionario = funcionarioRepository.findByUsuario(usuario)
                .orElseThrow(() -> new CredenciaisInvalidasException("Credenciais inválidas"));
        Funcionario.Tipo tipoEnum = Funcionario.Tipo.valueOf(tipo);

        if (!funcionario.getTipo().equals(tipoEnum)) {
            throw new CredenciaisInvalidasException("Credenciais inválidas");
        }
        if (!passwordEncoder.matches(senha, funcionario.getSenha())) {
            throw new CredenciaisInvalidasException("Credenciais inválidas");
        }
        return funcionarioMapper.funcionarioToResponse(funcionario);
    }
}
