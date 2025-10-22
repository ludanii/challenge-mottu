package br.com.fiap.challengemottu.service;

import br.com.fiap.challengemottu.dto.MotoRequest;
import br.com.fiap.challengemottu.dto.MotoResponse;
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
import java.util.Optional;

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

    public MotoResponse save(MotoRequest motoRequest) {
        Patio patio = patioRepository.findById(motoRequest.idPatio())
                .orElseThrow(() -> new RuntimeException("Pátio não encontrado com ID: " + motoRequest.idPatio()));

        Cliente cliente = null;
        if (motoRequest.idCliente() != null) {
            cliente = clienteRepository.findById(motoRequest.idCliente())
                    .orElseThrow(
                            () -> new RuntimeException("Cliente não encontrado com ID: " + motoRequest.idCliente()));
        }

        Moto moto = motoMapper.requestToMoto(motoRequest, patio, cliente);
        Moto savedMoto = motoRepository.save(moto);
        return motoMapper.motoToResponse(savedMoto);
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
                .orElse(null);
    }

    public MotoResponse update(MotoRequest motoRequest, Long id) {
        Optional<Moto> motoOptional = motoRepository.findById(id);

        if (motoOptional.isPresent()) {
            Moto moto = motoOptional.get();

            moto.setPlaca(motoRequest.placa());
            moto.setModelo(motoRequest.modelo());
            moto.setStatus(motoRequest.status());
            moto.setDisponibilidade(motoRequest.disponibilidade());
            moto.setVaga(motoRequest.vaga());
            moto.setCondicao(motoRequest.condicao());

            Patio patio = patioRepository.findById(motoRequest.idPatio())
                    .orElseThrow(() -> new RuntimeException("Pátio não encontrado com ID: " + motoRequest.idPatio()));
            moto.setLocalizacao(patio);

            if (motoRequest.idCliente() != null) {
                Cliente cliente = clienteRepository.findById(motoRequest.idCliente())
                        .orElseThrow(() -> new RuntimeException(
                                "Cliente não encontrado com ID: " + motoRequest.idCliente()));
                moto.setCliente(cliente);
            } else {
                moto.setCliente(null);
            }

            Moto motoAtualizada = motoRepository.save(moto);
            return motoMapper.motoToResponse(motoAtualizada);
        }

        return null;
    }

    public boolean delete(Long id) {
        Optional<Moto> moto = motoRepository.findById(id);
        if (moto.isPresent()) {
            motoRepository.delete(moto.get());
            return true;
        }
        return false;
    }
}