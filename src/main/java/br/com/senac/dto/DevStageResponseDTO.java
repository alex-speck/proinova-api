package br.com.senac.dto;

import br.com.senac.entity.DevStage;

public record DevStageResponseDTO(Long id, String stage) {
    public DevStageResponseDTO(DevStage dev){
        this(dev.getId(), dev.getStage());
    }
}
