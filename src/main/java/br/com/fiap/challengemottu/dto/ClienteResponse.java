package br.com.fiap.challengemottu.dto;

import java.util.List;

public record ClienteResponse(
                Long id,
                String nome,
                String email,
                String cpf,
                String telefone,
                Integer idade,
                List<Long> motosIds
        ) {
}
