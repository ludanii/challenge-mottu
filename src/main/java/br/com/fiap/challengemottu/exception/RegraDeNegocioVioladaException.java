package br.com.fiap.challengemottu.exception;

public class RegraDeNegocioVioladaException extends RuntimeException {
  public RegraDeNegocioVioladaException(String mensagem) {
    super(mensagem);
  }
}