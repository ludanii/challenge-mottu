package br.com.fiap.challengemottu.mapper;

import br.com.fiap.challengemottu.dto.AgendamentoResponse;
import br.com.fiap.challengemottu.model.Agendamento;

public class AgendamentoMapper {
    public Agendamento requetToAgendamento(String descricao) {
        return new Agendamento(descricao);
    }

    public AgendamentoResponse agendamentoToResponse(Agendamento agendamento) {
        return new AgendamentoResponse(
                agendamento.getIdAgendamento(),
                agendamento.getDescricao(),
                agendamento.getDataInicio(),
                agendamento.getDataFim());
    }
}
