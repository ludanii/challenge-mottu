package br.com.fiap.challengemottu.service;

import br.com.fiap.challengemottu.dto.MotoRequest;
import br.com.fiap.challengemottu.dto.MotoResponse;
import br.com.fiap.challengemottu.mapper.MotoMapper;
import br.com.fiap.challengemottu.model.Moto;
import br.com.fiap.challengemottu.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MotoService {
    private final MotoRepository motoRepository;
    private final MotoMapper motoMapper = new MotoMapper();

    @Autowired
    public MotoService(MotoRepository motoRepository) {
        this.motoRepository = motoRepository;
    }

    public MotoResponse save(MotoRequest motoRequest) {
        return motoMapper.motoToResponse(motoRepository.save(motoMapper.requestToMoto(motoRequest)));
    }

    public Page<MotoResponse> findAll(Pageable pageable) {
        return motoRepository.findAll(pageable).map(motoMapper::motoToResponse);
    }

    public Moto findMotoById(Long id) {
        Optional<Moto> moto = motoRepository.findById(id);
        return moto.orElse(null);
    }

    public MotoResponse findById(Long id) {
        Optional<Moto> moto = motoRepository.findById(id);
        return moto.map(motoMapper::motoToResponse).orElse(null);
    }

    public MotoResponse update(MotoRequest motoRequest, Long id) {
        Optional<Moto> motoOptional = motoRepository.findById(id);
        if (motoOptional.isPresent()) {
            Moto moto = motoOptional.get();
            moto.setModelo(motoRequest.modelo());
            moto.setPlaca(motoRequest.placa());
            moto.setStatus(motoRequest.status());
            moto.setSetor(motoRequest.setor());

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