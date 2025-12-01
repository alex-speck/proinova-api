package br.com.senac.commom.handler;

import br.com.senac.dto.error.ErroCampo;
import br.com.senac.dto.error.ErroResposta;
import br.com.senac.exception.PasswordsDontMatchException;
import br.com.senac.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

    @RestControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErroResposta> handleValidacao(MethodArgumentNotValidException ex) {
            List<ErroCampo> erros = ex.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(f -> new ErroCampo(f.getField(), f.getDefaultMessage()))
                    .toList();

            ErroResposta resposta = new ErroResposta(
                    HttpStatus.BAD_REQUEST.value(),
                    "Erro de validação",
                    erros,
                    System.currentTimeMillis()
            );

            return ResponseEntity.badRequest().body(resposta);
        }

        @ExceptionHandler(UserAlreadyExistsException.class)
        public ResponseEntity<ErroResposta> handleUserAlreadyExists(UserAlreadyExistsException ex){
            ErroResposta resposta = new ErroResposta(
                    HttpStatus.CONFLICT.value(),
                    ex.getLocalizedMessage(),
                    List.of(),
                    System.currentTimeMillis()
            );
            return ResponseEntity.badRequest().body(resposta);
        }

        @ExceptionHandler(PasswordsDontMatchException.class)
        public ResponseEntity<ErroResposta> handlePasswordsDontMatch(PasswordsDontMatchException ex){
            ErroResposta resposta = new ErroResposta(
                    HttpStatus.BAD_REQUEST.value(),
                    ex.getLocalizedMessage(),
                    List.of(),
                    System.currentTimeMillis()
            );
            return ResponseEntity.badRequest().body(resposta);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErroResposta> handleGenerico(Exception ex) {
            ErroResposta resposta = new ErroResposta(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Erro interno do servidor",
                    List.of(),
                    System.currentTimeMillis()
            );

            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resposta);
        }
    }

