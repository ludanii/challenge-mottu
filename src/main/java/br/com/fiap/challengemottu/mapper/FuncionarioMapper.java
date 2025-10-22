package br.com.fiap.challengemottu.mapper;

import java.util.Optional;

import br.com.fiap.challengemottu.dto.FuncionarioRequest;
import br.com.fiap.challengemottu.dto.FuncionarioResponse;
import br.com.fiap.challengemottu.model.Funcionario;
import br.com.fiap.challengemottu.model.Patio;

public class FuncionarioMapper {

    public Funcionario requestToFuncionario(FuncionarioRequest funcionarioRequest, Patio patio) {
        Funcionario funcionario = new Funcionario();
        funcionario.setUsuario(funcionarioRequest.usuario());
        funcionario.setNome(funcionarioRequest.nome());
        funcionario.setEmail(funcionarioRequest.email());
        funcionario.setSenha(funcionarioRequest.senha());
        funcionario.setTipo(funcionarioRequest.tipo());
        funcionario.setPatio(patio);
        return funcionario;
    }

    public FuncionarioResponse funcionarioToResponse(Funcionario funcionario) {
        Long patioId = Optional.ofNullable(funcionario.getPatio())
                .map(Patio::getId)
                .orElse(null);
        return new FuncionarioResponse(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getUsuario(),
                funcionario.getEmail(),
                funcionario.getTipo(),
                patioId);
    }
}
