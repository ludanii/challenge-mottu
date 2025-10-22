package br.com.fiap.challengemottu.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados de login do funcionário")
public record LoginRequest(
    @NotBlank(message = "O usuário é obrigatório") String usuario,

    @NotBlank(message = "A senha é obrigatória") String senha,

    @NotBlank(message = "O tipo é obrigatório (Funcionario ou Gerente)") String tipo) {
}
