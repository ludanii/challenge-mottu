package br.com.fiap.challengemottu.dto;

import java.time.LocalDate;

public record AgendamentoRequest(
        String descricao,
        LocalDate dataInicio,
        LocalDate dataFim
) {
}
