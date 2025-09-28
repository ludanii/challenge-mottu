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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return patioRepository.findAllWithFuncionarios()
                .stream()
                .map(patioMapper::patioToResponse)
                .toList();
    }

    public PatioResponse findById(Long id) {
        Optional<Patio> patio = patioRepository.findById(id);
        return patio.map(patioMapper::patioToResponse).orElse(null);
    }

    @Transactional
    public PatioResponse update(PatioRequest patioRequest, Long id) {
        Patio patio = patioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pátio não encontrado"));

        patio.setLogradouro(patioRequest.logradouro());
        patio.setCapacidade(patioRequest.capacidade());
        patio.setNome(patioRequest.nome());

        List<Funcionario> funcionariosAtuais = new ArrayList<>(patio.getFuncionarios());

        List<Funcionario> novosFuncionarios = funcionarioRepository.findAllById(patioRequest.funcionariosIds());

        for (Funcionario f : funcionariosAtuais) {
            if (!novosFuncionarios.contains(f)) {
                f.setPatio(null);
                funcionarioRepository.save(f);
            }
        }
        for (Funcionario f : novosFuncionarios) {
            f.setPatio(patio);
            funcionarioRepository.save(f);
        }
        patio.setFuncionarios(novosFuncionarios);

        Patio salvo = patioRepository.save(patio);
        return patioMapper.patioToResponse(salvo);
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
