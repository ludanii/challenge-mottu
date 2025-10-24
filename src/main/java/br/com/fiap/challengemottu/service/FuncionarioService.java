package br.com.fiap.challengemottu.service;

import br.com.fiap.challengemottu.dto.FuncionarioRequest;
import br.com.fiap.challengemottu.dto.FuncionarioResponse;
import br.com.fiap.challengemottu.dto.LoginResponse;
import br.com.fiap.challengemottu.exception.CredenciaisInvalidasException;
import br.com.fiap.challengemottu.exception.RecursoNaoEncontradoException;
import br.com.fiap.challengemottu.exception.RegraDeNegocioVioladaException;
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

    private void validarUnicidade(FuncionarioRequest funcionarioRequest, Long funcionarioId) {
        if (funcionarioRepository.existsByUsuarioAndIdNot(funcionarioRequest.usuario(), funcionarioId)) {
            throw new RegraDeNegocioVioladaException("Já existe um funcionário com este usuário.");
        }
        if (funcionarioRepository.existsByEmailAndIdNot(funcionarioRequest.email(), funcionarioId)) {
            throw new RegraDeNegocioVioladaException("Já existe um funcionário com este e-mail.");
        }
    }

    public FuncionarioResponse save(FuncionarioRequest funcionarioRequest) {
        validarUnicidade(funcionarioRequest, null);
        Patio patio = patioRepository
                .findById(funcionarioRequest.idPatio())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Pátio não encontrado com ID: " + funcionarioRequest.idPatio()));
        Funcionario funcionario = funcionarioMapper.requestToFuncionario(funcionarioRequest, patio);
        funcionario.setSenha(passwordEncoder.encode(funcionarioRequest.senha()));
        return funcionarioMapper.funcionarioToResponse(funcionarioRepository.save(funcionario));
    }

    public List<FuncionarioResponse> findAll() {
        return funcionarioRepository.findAll()
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

        funcionario.setUsuario(funcionarioRequest.usuario());
        funcionario.setNome(funcionarioRequest.nome());
        funcionario.setEmail(funcionarioRequest.email());
        funcionario.setTipo(funcionarioRequest.tipo());

        return funcionarioMapper.funcionarioToResponse(funcionarioRepository.save(funcionario));
    }

    public void delete(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Funcionário não encontrado com o ID: " + id);
        }
        funcionarioRepository.deleteById(id);
    }

    public LoginResponse login(String usuario, String senha, String tipo) {
        Funcionario funcionario = funcionarioRepository.findByUsuario(usuario)
                .orElseThrow(() -> new CredenciaisInvalidasException("Credenciais inválidas"));
        Funcionario.Tipo tipoEnum = Funcionario.Tipo.valueOf(tipo);

        if (!funcionario.getTipo().equals(tipoEnum)) {
            throw new CredenciaisInvalidasException("Credenciais inválidas");
        }
        if (!passwordEncoder.matches(senha, funcionario.getSenha())) {
            throw new CredenciaisInvalidasException("Credenciais inválidas");
        }
        return funcionarioMapper.loginToResponse(funcionario, funcionario.getPatio(),
                funcionario.getTipo().equals(Funcionario.Tipo.GERENTE));
    }
}
