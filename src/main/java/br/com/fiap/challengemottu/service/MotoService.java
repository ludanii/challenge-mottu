package br.com.fiap.challengemottu.service;

import br.com.fiap.challengemottu.dto.MotoRequest;
import br.com.fiap.challengemottu.dto.MotoResponse;
import br.com.fiap.challengemottu.exception.RecursoNaoEncontradoException;
import br.com.fiap.challengemottu.exception.RegraDeNegocioVioladaException;
import br.com.fiap.challengemottu.mapper.MotoMapper;
import br.com.fiap.challengemottu.model.Cliente;
import br.com.fiap.challengemottu.model.Moto;
import br.com.fiap.challengemottu.model.Patio;
import br.com.fiap.challengemottu.repository.ClienteRepository;
import br.com.fiap.challengemottu.repository.MotoRepository;
import br.com.fiap.challengemottu.repository.PatioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    private final MotoRepository motoRepository;
    private final PatioRepository patioRepository;
    private final ClienteRepository clienteRepository;
    private final MotoMapper motoMapper = new MotoMapper();

    @Autowired
    public MotoService(MotoRepository motoRepository, PatioRepository patioRepository,
            ClienteRepository clienteRepository) {
        this.motoRepository = motoRepository;
        this.patioRepository = patioRepository;
        this.clienteRepository = clienteRepository;
    }

     private void validarUnicidade(MotoRequest motoRequest, Long motoId) {
        if (motoRepository.existsByPlacaAndIdNot(motoRequest.placa(), motoId)) {
            throw new RegraDeNegocioVioladaException("Já existe uma moto com esta placa.");
        }
    }

    public MotoResponse save(MotoRequest motoRequest) {
        validarUnicidade(motoRequest, null);
        Patio patio = patioRepository.findById(motoRequest.idPatio()).orElseThrow(
                () -> new RecursoNaoEncontradoException("Pátio não encontrado com ID: " + motoRequest.idPatio()));

        Cliente cliente = null;
        if (motoRequest.idCliente() != null) {
            cliente = clienteRepository.findById(motoRequest.idCliente()).orElseThrow(
                    () -> new RecursoNaoEncontradoException(
                            "Cliente não encontrado com ID: " + motoRequest.idCliente()));
        }

        Moto moto = motoMapper.requestToMoto(motoRequest, patio, cliente);
        return motoMapper.motoToResponse(motoRepository.save(moto));
    }

    public List<MotoResponse> findAll() {
        return motoRepository.findAll()
                .stream()
                .map(motoMapper::motoToResponse)
                .toList();
    }

    public MotoResponse findById(Long id) {
        return motoRepository.findById(id)
                .map(motoMapper::motoToResponse)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Moto não encontrada com o ID: " + id));
    }

    public MotoResponse update(MotoRequest motoRequest, Long id) {
        validarUnicidade(motoRequest, id);
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Moto não encontrada com o ID: " + id));

        Patio patio = patioRepository.findById(motoRequest.idPatio()).orElseThrow(
                () -> new RecursoNaoEncontradoException("Pátio não encontrado com ID: " + motoRequest.idPatio()));

        Cliente cliente = null;
        if (motoRequest.idCliente() != null) {
            cliente = clienteRepository.findById(motoRequest.idCliente()).orElseThrow(
                    () -> new RecursoNaoEncontradoException(
                            "Cliente não encontrado com ID: " + motoRequest.idCliente()));
        }

        moto.setPlaca(motoRequest.placa());
        moto.setModelo(motoRequest.modelo());
        moto.setStatus(motoRequest.status());
        moto.setDisponibilidade(motoRequest.disponibilidade());
        moto.setVaga(motoRequest.vaga());
        moto.setCondicao(motoRequest.condicao());
        moto.setPatio(patio);
        moto.setCliente(cliente);

        return motoMapper.motoToResponse(motoRepository.save(moto));

    }

    public void delete(Long id) {
        if (!motoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Moto não encontrada com o ID: " + id);
        }
        motoRepository.deleteById(id);
    }
}