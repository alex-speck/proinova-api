package br.com.senac.dto;

import jakarta.validation.constraints.NotBlank;

public record UserPictureUpdateRequestDTO (@NotBlank String imageUrl) {
}
