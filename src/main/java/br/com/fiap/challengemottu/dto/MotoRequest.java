package br.com.fiap.challengemottu.dto;

import br.com.fiap.challengemottu.model.Moto.Modelo;
import br.com.fiap.challengemottu.model.Moto.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MotoRequest(
                @NotBlank(message = "A placa da moto é obrigatória.") String placa,
                @NotNull(message = "O modelo da moto é obrigatório.") Modelo modelo,
                @NotNull(message = "O status da moto é obrigatório.") Status status,
                Boolean disponibilidade,
                Integer vaga,
                String imagem,
                @NotNull(message = "A localização da moto é obrigatório.") Long patioId,
                Long clienteId
) {
}
