package br.com.senac.dto;

import br.com.senac.entity.Project;

import java.math.BigDecimal;
import java.util.List;

public record ProjectResponseDTO(Long id,
                                 String title,
                                 String description,
                                 BigDecimal currentFund,
                                 BigDecimal fundGoal,
                                 List<String> members,
                                 String imageUrl,
                                 ActivityAreaResponseDTO activityArea,
                                 DevStageResponseDTO devStage) {
    public ProjectResponseDTO(Project project) {
        this(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getCurrentFund(),
                project.getFundGoal(),
                project.getMembers(),
                project.getImageUrl(),
                new ActivityAreaResponseDTO(project.getActivityArea()),
                new DevStageResponseDTO(project.getDevStage())
        );
    }
}
