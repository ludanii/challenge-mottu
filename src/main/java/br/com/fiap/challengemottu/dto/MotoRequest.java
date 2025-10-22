package br.com.fiap.challengemottu.dto;

import br.com.fiap.challengemottu.model.Moto.Condicao;
import br.com.fiap.challengemottu.model.Moto.Modelo;
import br.com.fiap.challengemottu.model.Moto.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MotoRequest(
        @NotBlank(message = "A placa da moto é obrigatória.") @Pattern(regexp = "^[A-Z]{3}[0-9][0-9A-Z][0-9]{2}$", message = "A placa deve estar no formato válido (ex: ABC1234 ou BRA2E19).") String placa,
        @NotNull(message = "O modelo da moto é obrigatório.") Modelo modelo,
        @NotNull(message = "A condicao da moto é obrigatório.") Condicao condicao,
        Boolean disponibilidade,
        Integer vaga,
        Status status,
        @NotNull(message = "A localização da moto é obrigatório.") Long idPatio,
        Long idCliente) {
}
