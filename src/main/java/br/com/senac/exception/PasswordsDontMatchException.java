package br.com.senac.exception;

public class PasswordsDontMatchException extends RuntimeException {
    public PasswordsDontMatchException(String message) {
        super(message);
    }
}
