package br.com.senac.dto.error;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResposta(int status, String mensagem, List<ErroCampo> erros, long timestamp) {

    public static ErroResposta respostaPadrao(String mensagem) {
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(), mensagem, List.of(), System.currentTimeMillis());
    }

    public static ErroResposta conflito(String mensagem) {
        return new ErroResposta(HttpStatus.CONFLICT.value(), mensagem, List.of(), System.currentTimeMillis());
    }
}
