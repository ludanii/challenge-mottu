package br.com.fiap.challengemottu.service;

import br.com.fiap.challengemottu.dto.FuncionarioRequest;
import br.com.fiap.challengemottu.dto.FuncionarioResponse;
import br.com.fiap.challengemottu.mapper.FuncionarioMapper;
import br.com.fiap.challengemottu.model.Funcionario;
import br.com.fiap.challengemottu.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper = new FuncionarioMapper();

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public FuncionarioResponse save(FuncionarioRequest funcionarioRequest) {
        return funcionarioMapper.funcionarioToResponse(funcionarioRepository.save(funcionarioMapper.requestToFuncionario(funcionarioRequest)));
    }

    public Page<FuncionarioResponse> findAll(Pageable pageable) {
        return funcionarioRepository.findAll(pageable).map(funcionarioMapper::funcionarioToResponse);
    }

    public Funcionario findFuncionarioById(Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return funcionario.orElse(null);
    }

    public FuncionarioResponse findById(Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return funcionario.map(funcionarioMapper::funcionarioToResponse).orElse(null);
    }

    public FuncionarioResponse update(FuncionarioRequest funcionarioRequest, Long id) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);

        if (funcionarioOptional.isPresent()) {
            Funcionario funcionario = funcionarioOptional.get();
            funcionario.setNomeUsuario(funcionarioRequest.nomeUsuario());
            funcionario.setEmail(funcionarioRequest.email());
            funcionario.setSenha(funcionarioRequest.senha());

            Funcionario funcionarioAtualizado = funcionarioRepository.save(funcionario);
            return funcionarioMapper.funcionarioToResponse(funcionarioAtualizado);
        }

        return null;
    }

    public boolean delete(Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        if (funcionario.isPresent()) {
            funcionarioRepository.delete(funcionario.get());
            return true;
        }
        return false;
    }
}
