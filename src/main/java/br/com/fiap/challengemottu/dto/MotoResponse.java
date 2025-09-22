package br.com.fiap.challengemottu.dto;

import br.com.fiap.challengemottu.model.Moto.Modelo;
import br.com.fiap.challengemottu.model.Moto.Status;

public record MotoResponse(
    Long id,
    String placa,
    Modelo modelo,
    Status status,
    Boolean disponibilidade,
    Integer vaga,
    String imagem,
    Long patioId,
    Long clienteId
) {
}