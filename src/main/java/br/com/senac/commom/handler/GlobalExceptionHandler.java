package br.com.senac.commom.handler;

import br.com.senac.dto.error.ErrorField;
import br.com.senac.dto.error.ErrorResponse;
import br.com.senac.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

    @RestControllerAdvice
    public class GlobalExceptionHandler {

        private OffsetDateTime timestamp() {
            return OffsetDateTime.now(ZoneId.of("America/Sao_Paulo"));
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> handleValidacao(MethodArgumentNotValidException ex) {
            List<ErrorField> erros = ex.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(f -> new ErrorField(f.getField(), f.getDefaultMessage()))
                    .toList();

            ErrorResponse resposta = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Erro de validação",
                    erros,
                    timestamp()
            );

            ex.printStackTrace();
            return ResponseEntity.badRequest().body(resposta);
        }

        @ExceptionHandler(UserAlreadyExistsException.class)
        public ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExistsException ex){
            ErrorResponse resposta = new ErrorResponse(
                    HttpStatus.CONFLICT.value(),
                    ex.getLocalizedMessage(),
                    List.of(),
                    timestamp()
            );

            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(resposta);
        }

        @ExceptionHandler(DuplicateEntryException.class)
        public ResponseEntity<ErrorResponse> handleDuplicateEntryException(DuplicateEntryException ex){
            ErrorResponse resposta = new ErrorResponse(
                    HttpStatus.CONFLICT.value(),
                    ex.getLocalizedMessage(),
                    List.of(),
                    timestamp()
            );

            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(resposta);
        }

        @ExceptionHandler(ElementNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleElementNotFoundException(ElementNotFoundException ex){
            ErrorResponse resposta = new ErrorResponse(
                    HttpStatus.NOT_FOUND.value(),
                    ex.getLocalizedMessage(),
                    List.of(),
                    timestamp()
            );

            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }

        @ExceptionHandler(UndeletableElementException.class)
        public ResponseEntity<ErrorResponse> handleUndeletableElementException(UndeletableElementException ex){
            ErrorResponse resposta = new ErrorResponse(
                    HttpStatus.FORBIDDEN.value(),
                    ex.getLocalizedMessage(),
                    List.of(),
                    timestamp()
            );

            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(resposta);
        }

        @ExceptionHandler(PasswordsDontMatchException.class)
        public ResponseEntity<ErrorResponse> handlePasswordsDontMatch(PasswordsDontMatchException ex){
            ErrorResponse resposta = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    ex.getLocalizedMessage(),
                    List.of(),
                    timestamp()
            );

            ex.printStackTrace();
            return ResponseEntity.badRequest().body(resposta);
        }

        @ExceptionHandler(ForbiddenException.class)
        public ResponseEntity<ErrorResponse> handleForbiddenException(ForbiddenException ex){
            ErrorResponse resposta = new ErrorResponse(
                    HttpStatus.FORBIDDEN.value(),
                    ex.getLocalizedMessage(),
                    List.of(),
                    timestamp()
            );

            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(resposta);
        }

        @ExceptionHandler(ImageUploadException.class)
        public ResponseEntity<ErrorResponse> handleImageUploadException(ImageUploadException ex){
            ErrorResponse resposta = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    ex.getLocalizedMessage(),
                    List.of(),
                    timestamp()
            );

            ex.printStackTrace();
            return ResponseEntity.badRequest().body(resposta);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleGenerico(Exception ex) {
            ErrorResponse resposta = new ErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Erro interno do servidor",
                    List.of(),
                    timestamp()
            );

            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resposta);
        }

    }

