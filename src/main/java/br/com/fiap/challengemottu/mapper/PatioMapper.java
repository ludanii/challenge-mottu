package br.com.fiap.challengemottu.mapper;

import br.com.fiap.challengemottu.dto.PatioRequest;
import br.com.fiap.challengemottu.dto.PatioResponse;
import br.com.fiap.challengemottu.model.Funcionario;
import br.com.fiap.challengemottu.model.Moto;
import br.com.fiap.challengemottu.model.Patio;

import java.util.List;

public class PatioMapper {

    public Patio requestToPatio(PatioRequest patioRequest) {
        Patio patio = new Patio();
        patio.setLogradouro(patioRequest.logradouro());
        patio.setCapacidade(patioRequest.capacidade());
        patio.setNome(patioRequest.nome());
        return patio;
    }

    public PatioResponse patioToResponse(Patio patio) {
        List<Long> funcionariosIds = patio.getFuncionarios()
                .stream()
                .map(Funcionario::getId)
                .toList();
        List<Long> motosIds = patio.getMotos()
                .stream()
                .map(Moto::getId)
                .toList();
        return new PatioResponse(
                patio.getId(),
                patio.getLogradouro(),
                patio.getCapacidade(),
                patio.getNome(),
                funcionariosIds,
                motosIds);
    }
}
