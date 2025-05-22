package br.com.fiap.challengemottu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MotoRequest(
        @NotBlank(message = "O modelo da moto é obrigatório.") String modelo,
        @NotNull(message = "A placa da moto é obrigatória.") String placa,
        @NotBlank(message = "O status da moto é obrigatório (Disponível, Alugada ou em manutenção).") String status,
        @NotBlank(message = "O setor da moto é obrigatório (bom, intermediário ou ruim).") String setor
) {
}
