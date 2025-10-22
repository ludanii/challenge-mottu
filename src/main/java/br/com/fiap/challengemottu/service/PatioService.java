package br.com.fiap.challengemottu.service;

import br.com.fiap.challengemottu.dto.PatioRequest;
import br.com.fiap.challengemottu.dto.PatioResponse;
import br.com.fiap.challengemottu.exception.RecursoNaoEncontradoException;
import br.com.fiap.challengemottu.mapper.PatioMapper;
import br.com.fiap.challengemottu.model.Patio;
import br.com.fiap.challengemottu.repository.PatioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatioService {

    private final PatioRepository patioRepository;
    private final PatioMapper patioMapper = new PatioMapper();

    @Autowired
    public PatioService(PatioRepository patioRepository) {
        this.patioRepository = patioRepository;
    }

    public PatioResponse save(PatioRequest patioRequest) {
        Patio patio = patioMapper.requestToPatio(patioRequest);
        return patioMapper.patioToResponse(patioRepository.save(patio));
    }

    public List<PatioResponse> findAll() {
        return patioRepository.findAllWithFuncionarios()
                .stream()
                .map(patioMapper::patioToResponse)
                .toList();
    }

    public PatioResponse findById(Long id) {
        return patioRepository.findById(id)
                .map(patioMapper::patioToResponse)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pátio não encontrado com o ID: " + id));
    }

    public PatioResponse update(PatioRequest patioRequest, Long id) {
        Patio patio = patioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pátio não encontrado com o ID: " + id));

        patio.setLogradouro(patioRequest.logradouro());
        patio.setCapacidade(patioRequest.capacidade());
        patio.setNome(patioRequest.nome());

        return patioMapper.patioToResponse(patioRepository.save(patio));
    }

    public void delete(Long id) {
        if (!patioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Pátio não encontrado com o ID: " + id);
        }
        patioRepository.deleteById(id);
    }

}
