package br.com.fiap.challengemottu.mapper;

import br.com.fiap.challengemottu.dto.MotoRequest;
import br.com.fiap.challengemottu.dto.MotoResponse;
import br.com.fiap.challengemottu.model.Cliente;
import br.com.fiap.challengemottu.model.Moto;
import br.com.fiap.challengemottu.model.Patio;

public class MotoMapper {

    public Moto requestToMoto(MotoRequest motoRequest, Patio patioId, Cliente clienteId) {
        Moto moto = new Moto();
        moto.setPlaca(motoRequest.placa());
        moto.setModelo(motoRequest.modelo());
        moto.setStatus(motoRequest.status());
        moto.setDisponibilidade(motoRequest.disponibilidade());
        moto.setVaga(motoRequest.vaga());
        moto.setImagem(motoRequest.imagem());
        moto.setLocalizacao(patioId);
        moto.setCliente(clienteId);
        return moto;
    }

    public MotoResponse motoToResponse(Moto moto) {
        return new MotoResponse(
                moto.getId(),
                moto.getPlaca(),
                moto.getModelo(),
                moto.getStatus(),
                moto.getDisponibilidade(),
                moto.getVaga(),
                moto.getImagem(),
                moto.getLocalizacao().getId(),
                moto.getCliente().getId());
    }
}
