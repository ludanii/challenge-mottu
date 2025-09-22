package br.com.fiap.challengemottu.dto;

public record ClienteResponse(
                Long id,
                String nome,
                String email,
                String cpf,
                String telefone,
                Integer idade
        ) {
}
