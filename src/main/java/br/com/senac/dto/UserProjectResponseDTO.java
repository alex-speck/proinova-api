package br.com.senac.dto;

import br.com.senac.entity.ActivityArea;
import br.com.senac.entity.DevStage;
import br.com.senac.entity.Project;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record UserProjectResponseDTO(Long id,
                                     String title,
                                     String description,
                                     BigDecimal currentFund,
                                     BigDecimal fundGoal,
                                     List<String> members,
                                     String imageUrl,
                                     ActivityArea activityArea,
                                     DevStage devStage,
                                     LocalDateTime createdAt,
                                     LocalDateTime updatedAt) {
    public UserProjectResponseDTO(Project project) {
        this(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getCurrentFund(),
                project.getFundGoal(),
                project.getMembers(),
                project.getImageUrl(),
                project.getActivityArea(),
                project.getDevStage(),
                project.getCreatedAt(),
                project.getUpdatedAt()
        );
    }
}
