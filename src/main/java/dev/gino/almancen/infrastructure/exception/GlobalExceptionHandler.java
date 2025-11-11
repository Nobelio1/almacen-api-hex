package dev.gino.almancen.infrastructure.exception;

import dev.gino.almancen.domain.exception.NoExisteProductoException;
import dev.gino.almancen.domain.exception.RetiroNoValidoException;
import dev.gino.almancen.domain.exception.SkuDuplicadoException;
import dev.gino.almancen.infrastructure.exception.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SkuDuplicadoException.class)
    public ResponseEntity<ErrorResponse> handleSkuDuplicadoException(SkuDuplicadoException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "CONFLICT",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(NoExisteProductoException.class)
    public ResponseEntity<ErrorResponse> handleNoProductoExistsException(NoExisteProductoException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "BAD_REQUEST",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(RetiroNoValidoException.class)
    public ResponseEntity<ErrorResponse> handleRetiroNovalidoException(RetiroNoValidoException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "CONFLICT",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
