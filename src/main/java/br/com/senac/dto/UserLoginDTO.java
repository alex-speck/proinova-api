package br.com.senac.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserLoginDTO(@Email(message = "Deve ser um email valido!")
                           String email,
                           @NotNull(message = "Senha invalida")
                           @Size(min = 8, message = "Senha invalida")
                           String password) {
}
