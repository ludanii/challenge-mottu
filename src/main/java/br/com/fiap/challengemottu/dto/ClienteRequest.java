package br.com.fiap.challengemottu.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record ClienteRequest(
        @NotBlank(message = "O nome é obrigatório.") String nome,
        @Email(message = "Email fora do formato correto.") String email,
        @CPF(message = "CPF fora do formato correto.") String cpf,
        @NotBlank(message = "O telefone é obrigatório.") String telefone,
        @NotNull(message = "A data de nascimento é obrigatória.") LocalDate dataNascimento
) {
}
