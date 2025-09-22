package br.com.fiap.challengemottu.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRequest(
                @NotBlank(message = "O nome é obrigatório.") String nome,
                @Email(message = "Email fora do formato correto.") String email,
                @CPF(message = "CPF fora do formato correto.") String cpf,
                @NotBlank(message = "O telefone é obrigatório.") String telefone,
                @Min(value = 18, message = "O cliente deve ser maior de 18 anos.") Integer idade) {
}
