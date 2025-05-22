package br.com.fiap.challengemottu.dto;

import java.time.LocalDate;

public record AgendamentoResponse(
        Long idAgendamento,
        String descricao,
        LocalDate dataInicio,
        LocalDate dataFim
) {
}
