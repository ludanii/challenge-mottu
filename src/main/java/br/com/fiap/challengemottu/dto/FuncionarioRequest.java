package br.com.fiap.challengemottu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FuncionarioRequest(
    @NotBlank(message = "O nome de usuário do funcionário é obrigatório.") String nomeUsuario,
    @NotNull(message = "O email do funcionário é obrigatório.") String email,
    @NotNull(message = "A senha do funcionário é obrigatório.") String senha
) {
}
