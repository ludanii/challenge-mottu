package br.com.fiap.challengemottu.mapper;

import br.com.fiap.challengemottu.dto.PatioRequest;
import br.com.fiap.challengemottu.dto.PatioResponse;
import br.com.fiap.challengemottu.model.Patio;

public class PatioMapper {

    public Patio requestToPatio(PatioRequest patioRequest) {
        Patio patio = new Patio();
        patio.setLocalizacao(patioRequest.localizacao());
        patio.setQuantidadeVagas(patioRequest.quantidadeVagas());
        return patio;
    }

    public PatioResponse patioToResponse(Patio patio) {
        return new PatioResponse(
                patio.getIdPatio(),
                patio.getLocalizacao(),
                patio.getQuantidadeVagas());
    }
}
