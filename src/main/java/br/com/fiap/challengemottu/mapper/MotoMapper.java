package br.com.fiap.challengemottu.mapper;

import br.com.fiap.challengemottu.dto.MotoRequest;
import br.com.fiap.challengemottu.dto.MotoResponse;
import br.com.fiap.challengemottu.model.Moto;

public class MotoMapper {

    public Moto requestToMoto(MotoRequest motoRequest) {
        Moto moto = new Moto();
        moto.setModelo(motoRequest.modelo());
        moto.setPlaca(motoRequest.placa());
        moto.setStatus(motoRequest.status());
        moto.setSetor(motoRequest.setor());
        return moto;
    }

    public MotoResponse motoToResponse(Moto moto) {
        return new MotoResponse(
                moto.getIdMoto(),
                moto.getModelo(),
                moto.getPlaca(),
                moto.getStatus(),
                moto.getSetor());
    }
}
