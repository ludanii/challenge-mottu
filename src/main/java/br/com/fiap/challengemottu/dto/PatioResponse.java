package br.com.fiap.challengemottu.dto;

import java.util.List;

public record PatioResponse(
                Long id,
                String logradouro,
                Integer capacidade,
                String nome,
                List<Long> funcionariosIds,
                List<Long> motosIds) {
}
