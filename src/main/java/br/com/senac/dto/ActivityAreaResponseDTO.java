package br.com.senac.dto;

import br.com.senac.entity.ActivityArea;

public record ActivityAreaResponseDTO(Long id, String area) {
    public ActivityAreaResponseDTO(ActivityArea activityArea){
        this(activityArea.getId(), activityArea.getArea());
    }
}
