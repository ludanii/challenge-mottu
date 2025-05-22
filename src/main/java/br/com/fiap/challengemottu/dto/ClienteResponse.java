package br.com.fiap.challengemottu.dto;

import java.time.LocalDate;

public record ClienteResponse(
        Long idCliente,
        String nome,
        String email,
        String cpf,
        String telefone,
        LocalDate dataNascimento
) {
}
