package br.com.fiap.challengemottu.service;

import br.com.fiap.challengemottu.dto.PatioRequest;
import br.com.fiap.challengemottu.dto.PatioResponse;
import br.com.fiap.challengemottu.mapper.PatioMapper;
import br.com.fiap.challengemottu.model.Funcionario;
import br.com.fiap.challengemottu.model.Patio;
import br.com.fiap.challengemottu.repository.FuncionarioRepository;
import br.com.fiap.challengemottu.repository.PatioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatioService {

    private final PatioRepository patioRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final PatioMapper patioMapper = new PatioMapper();

    @Autowired
    public PatioService(PatioRepository patioRepository, FuncionarioRepository funcionarioRepository) {
        this.patioRepository = patioRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public PatioResponse save(PatioRequest patioRequest) {
        List<Funcionario> funcionarios = funcionarioRepository.findAllById(patioRequest.funcionariosIds());
        Patio patio = patioMapper.requestToPatio(patioRequest, funcionarios);
        Patio savedPatio = patioRepository.save(patio);
        return patioMapper.patioToResponse(savedPatio);
    }

    public List<PatioResponse> findAll() {
        List<Patio> patios = patioRepository.findAll();
        return patios.stream()
                .map(patioMapper::patioToResponse)
                .collect(Collectors.toList());
    }

    public PatioResponse findById(Long id) {
        Optional<Patio> patio = patioRepository.findById(id);
        return patio.map(patioMapper::patioToResponse).orElse(null);
    }

    public PatioResponse update(PatioRequest patioRequest, Long id) {
        Optional<Patio> patioOptional = patioRepository.findById(id);
        if (patioOptional.isPresent()) {
            Patio patio = patioOptional.get();
            patio.setLogradouro(patioRequest.logradouro());
            patio.setCapacidade(patioRequest.capacidade());
            patio.setNome(patioRequest.nome());

            List<Funcionario> funcionarios = funcionarioRepository.findAllById(patioRequest.funcionariosIds());
            patio.setFuncionarios(funcionarios);

            Patio patioAtualizado = patioRepository.save(patio);
            return patioMapper.patioToResponse(patioAtualizado);
        }
        return null;
    }

    public boolean delete(Long id) {
        Optional<Patio> patio = patioRepository.findById(id);
        if (patio.isPresent()) {
            patioRepository.delete(patio.get());
            return true;
        }
        return false;
    }
}
