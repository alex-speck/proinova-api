package br.com.senac.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(@NotNull
                              @Size(min = 3, max = 12)
                              String username,
                              @NotNull
                              String name,
                              @Email
                              String email,
                              @Size(min = 3, max = 16)
                              String password,
                              String confirmPassword) {
}
