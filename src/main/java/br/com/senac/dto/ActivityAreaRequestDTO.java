package br.com.senac.dto;

import jakarta.validation.constraints.NotBlank;

public record ActivityAreaRequestDTO(@NotBlank(message = "O estagio de desenvolvimento não pode ser em branco")
                                     String area) {

}
