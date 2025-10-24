package br.com.fiap.challengemottu.dto;

public record LoginResponse(
  FuncionarioResponse funcionario,
  PatioResponse patio,
  Boolean isGerente
){}