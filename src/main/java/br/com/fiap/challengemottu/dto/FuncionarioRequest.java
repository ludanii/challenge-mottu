package br.com.fiap.challengemottu.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record FuncionarioRequest(
        @NotBlank(message = "O usuário do funcionário é obrigatório.") String usuario,
        @NotBlank(message = "O nome do funcionário é obrigatório.") String nome,
        @Email(message = "O email informado não é válido.") String email,
        @NotBlank(message = "A senha do funcionário é obrigatória.") String senha
        )
{}