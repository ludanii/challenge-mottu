package br.com.fiap.challengemottu.exception;

public class CredenciaisInvalidasException extends RuntimeException {
  public CredenciaisInvalidasException(String mensagem) {
    super(mensagem);
  }
}
