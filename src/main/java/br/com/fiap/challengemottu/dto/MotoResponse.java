package br.com.fiap.challengemottu.dto;

public record MotoResponse(
        Long idMoto,
        String modelo,
        String placa,
        String status,
        String setor
) {
}
