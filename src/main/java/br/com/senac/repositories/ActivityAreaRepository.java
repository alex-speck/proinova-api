package br.com.senac.repositories;

import br.com.senac.entity.ActivityArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActivityAreaRepository extends JpaRepository<ActivityArea, Long> {
    Optional<ActivityArea> findByArea(String area);
}
