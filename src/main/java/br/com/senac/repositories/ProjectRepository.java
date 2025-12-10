package br.com.senac.repositories;

import br.com.senac.entity.Project;
import br.com.senac.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByActivityAreaId (Long id);
    List<Project> findByDevStageId (Long id);
    boolean existsByActivityAreaId(Long id);
    boolean existsByDevStageId(Long id);
    List<Project> findByOwner(User owner);

    List<Project> findByTitleContainingIgnoreCase(String title);
}
