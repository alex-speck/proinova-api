package br.com.senac.dto.error;

import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

public record ErrorResponse(int status, String mensagem, List<ErrorField> erros, OffsetDateTime time) {

    public static ErrorResponse respostaPadrao(String mensagem) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), mensagem, List.of(), OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")) );
    }

    public static ErrorResponse conflito(String mensagem) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), mensagem, List.of(), OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")));
    }
}
