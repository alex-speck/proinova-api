package br.com.senac.dto;

import jakarta.validation.constraints.Size;

public record UserPasswordUpdateRequestDTO (String currentPass,
                                            @Size(min = 8, max = 16)
                                            String newPass,
                                            String confirmPass){
}
