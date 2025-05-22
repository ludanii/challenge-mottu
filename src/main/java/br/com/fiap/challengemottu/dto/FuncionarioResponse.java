package br.com.fiap.challengemottu.dto;

public record FuncionarioResponse(
        Long idFuncionario,
        String nomeUsuario,
        String email,
        String senha
) {
}
