package br.com.fiap.challengemottu.dto;

public record PatioResponse(
        Long idPatio,
        String localizacao,
        Integer quantidadeVagas
) {
}
