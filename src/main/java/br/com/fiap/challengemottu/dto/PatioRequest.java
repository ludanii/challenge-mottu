package br.com.fiap.challengemottu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record PatioRequest(
                @NotBlank(message = "A localização do pátio é obrigatória.") String logradouro,
                @NotNull(message = "A quantidade de vagas no pátio é obrigatória.") @PositiveOrZero(message = "A quantidade de vagas não pode ser negativa.") Integer capacidade,
                @NotBlank(message = "O nome do pátio é obrigatório.") String nome,
                List<Long> funcionariosIds
) {
}
