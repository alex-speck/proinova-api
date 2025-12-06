package br.com.senac.dto.error;

import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

public record ErroResposta(int status, String mensagem, List<ErroCampo> erros, OffsetDateTime time) {

    public static ErroResposta respostaPadrao(String mensagem) {
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(), mensagem, List.of(), OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")) );
    }

    public static ErroResposta conflito(String mensagem) {
        return new ErroResposta(HttpStatus.CONFLICT.value(), mensagem, List.of(), OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")));
    }
}
