package dev.gino.almancen.domain.exception;

public class SkuDuplicadoException extends RuntimeException {
    public SkuDuplicadoException(String message) {
        super(message);
    }
}
