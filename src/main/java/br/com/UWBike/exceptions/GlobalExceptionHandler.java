package br.com.UWBike.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<String> handleEnumConversionError(HttpMessageNotReadableException ex) {
            if (ex.getCause() instanceof InvalidFormatException) {
                return ResponseEntity.badRequest().body("Valor inválido para o enum TipoMoto.");
            }
            return ResponseEntity.badRequest().body("Requisição malformada.");
        }
}

