package br.com.senac.dto.error;

import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

public record ErrorResponse(int status, String message, List<ErrorField> errors, OffsetDateTime time) {

    public static ErrorResponse respostaPadrao(String message) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message, List.of(), OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")) );
    }

    public static ErrorResponse conflito(String message) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), message, List.of(), OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")));
    }
}
