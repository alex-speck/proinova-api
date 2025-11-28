package br.com.senac.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(@NotNull
                              @Size(max = 12)
                              String username,
                              @NotNull
                              String name,
                              @Email
                              String email,
                              @Size(min = 8)
                              String password,
                              String confirmPassword) {
}
