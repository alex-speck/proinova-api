package br.com.senac.exception;

public class UndeletableElementException extends RuntimeException {
    public UndeletableElementException(String message) {
        super(message);
    }
}
