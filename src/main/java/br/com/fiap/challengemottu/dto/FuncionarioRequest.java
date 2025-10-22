package br.com.fiap.challengemottu.dto;

import br.com.fiap.challengemottu.model.Funcionario.Tipo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FuncionarioRequest(
        @NotBlank(message = "O usuário do funcionário é obrigatório.") @Size(min = 3, message = "O usuário deve ter no mínimo 3 caracteres.") String usuario,
        @NotBlank(message = "O nome do funcionário é obrigatório.") @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres.") String nome,
        @Email(message = "O email informado não é válido.") @NotBlank(message = "O email do funcionário é obrigatório.") String email,
        @NotNull(message = "O tipo do funcionário é obrigatório.") Tipo tipo,
        @NotNull(message = "O ID do pátio é obrigatório.") Long patio,
        @NotBlank(message = "A senha do funcionário é obrigatória.") @Size(min = 4, message = "A senha deve ter no mínimo 4 caracteres.") String senha) {
}
