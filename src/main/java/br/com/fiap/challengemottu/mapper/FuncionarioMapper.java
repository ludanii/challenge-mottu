package br.com.fiap.challengemottu.mapper;

import br.com.fiap.challengemottu.dto.FuncionarioRequest;
import br.com.fiap.challengemottu.dto.FuncionarioResponse;
import br.com.fiap.challengemottu.model.Funcionario;

public class FuncionarioMapper {

    public Funcionario requestToFuncionario(FuncionarioRequest funcionarioRequest) {
        Funcionario funcionario = new Funcionario();
        funcionario.setUsuario(funcionarioRequest.usuario());
        funcionario.setNome(funcionarioRequest.nome());
        funcionario.setEmail(funcionarioRequest.email());
        funcionario.setSenha(funcionarioRequest.senha());
        return funcionario;
    }

    public FuncionarioResponse funcionarioToResponse(Funcionario funcionario) {
        return new FuncionarioResponse(
                funcionario.getId(),
                funcionario.getUsuario(),
                funcionario.getNome(),
                funcionario.getEmail(),
                funcionario.getSenha());
    }
}
