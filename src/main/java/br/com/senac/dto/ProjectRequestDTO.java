package br.com.senac.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record ProjectRequestDTO(@NotBlank(message = "O título é obrigatório")
                                @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
                                String title,
                                @NotBlank(message = "A descrição é obrigatória")
                                @Size(min = 10, max = 2000, message = "A descrição deve ter entre 10 e 2000 caracteres")
                                String description,
                                @NotNull(message = "O fundo atual é obrigatório")
                                @PositiveOrZero(message = "O fundo atual não pode ser negativo")
                                @Digits(integer = 12, fraction = 2, message = "fundo atual pode ter no máximo 12 dígitos e 2 decimais")
                                BigDecimal currentFund,
                                @NotNull(message = "A meta é obrigatória")
                                @Positive(message = "A meta deve ser maior que zero")
                                @Digits(integer = 12, fraction = 2, message = "A meta pode ter no máximo 12 dígitos e 2 decimais")
                                BigDecimal fundGoal,
                                @NotEmpty(message = "Projeto deve conter pelo menos 1 membro")
                                List<@NotBlank(message = "Nome do membro não pode ser vazio") String> members,
                                String imageUrl,
                                @NotNull(message = "Área de atividade é obrigatória")
                                Long activityAreaId,
                                @NotNull(message = "Estágio de desenvolvimento é obrigatório")
                                Long devStageId) {
}
