package br.com.senac.repositories;

import br.com.senac.entity.DevStage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DevStageRepository extends JpaRepository<DevStage, Long> {
    Optional<DevStage> findByStage(String stage);
}
