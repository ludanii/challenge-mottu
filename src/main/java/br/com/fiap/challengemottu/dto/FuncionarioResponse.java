package br.com.fiap.challengemottu.dto;

import br.com.fiap.challengemottu.model.Funcionario.Tipo;

public record FuncionarioResponse(
                Long id,
                String nome,
                String usuario,
                String email,
                Tipo tipo,
                Long idPatio
) {
}
