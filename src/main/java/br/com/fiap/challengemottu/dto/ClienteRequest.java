package br.com.fiap.challengemottu.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

public record ClienteRequest(
                @NotBlank(message = "O nome é obrigatório.") String nome,
                @Email(message = "Email fora do formato correto.") String email,
                @NotBlank(message = "O CPF é obrigatório.")
                @CPF(message = "CPF fora do formato correto.") String cpf,
                @NotBlank(message = "O telefone é obrigatório.")
                @Pattern(regexp = "^(\\+55)?\\s?(\\(?\\d{2}\\)?)\\s?\\d{4,5}\\-?\\d{4}$", message = "Telefone deve estar no formato (11)91234-5678 ou +55 (11) 91234-5678.")
                String telefone,
                @Min(value = 18, message = "O cliente deve ser maior de 18 anos.") Integer idade) {
}
