package dev.gino.almancen.domain.exception;

public class NoExisteProductoException extends RuntimeException {
    public NoExisteProductoException(String message) {
        super(message);
    }
}
