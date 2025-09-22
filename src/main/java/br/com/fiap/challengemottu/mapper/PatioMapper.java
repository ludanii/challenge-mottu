package br.com.fiap.challengemottu.mapper;

import br.com.fiap.challengemottu.dto.PatioRequest;
import br.com.fiap.challengemottu.dto.PatioResponse;
import br.com.fiap.challengemottu.model.Funcionario;
import br.com.fiap.challengemottu.model.Patio;

import java.util.List;
import java.util.stream.Collectors;

public class PatioMapper {

    public Patio requestToPatio(PatioRequest patioRequest, List<Funcionario> funcionarios) {
        Patio patio = new Patio();
        patio.setLogradouro(patioRequest.logradouro());
        patio.setCapacidade(patioRequest.capacidade());
        patio.setNome(patioRequest.nome());
        patio.setFuncionarios(funcionarios);
        return patio;
    }

    public PatioResponse patioToResponse(Patio patio) {
        List<Long> funcionariosIds = null;
        if (patio.getFuncionarios() != null) {
            funcionariosIds = patio.getFuncionarios()
                    .stream()
                    .map(Funcionario::getId)
                    .collect(Collectors.toList());
        }

        return new PatioResponse(
                patio.getId(),
                patio.getLogradouro(),
                patio.getCapacidade(),
                patio.getNome(),
                funcionariosIds);
    }
}
