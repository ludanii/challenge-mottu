package br.com.fiap.challengemottu.dto;
public record FuncionarioResponse(
        Long id,
        String nome,
        String usuario,
        String email,
        String senha
) {
}
