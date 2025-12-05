package br.com.senac.services;

import br.com.senac.dto.ProjectRequestDTO;
import br.com.senac.dto.ProjectResponseDTO;
import br.com.senac.dto.UserProjectResponseDTO;
import br.com.senac.entity.ActivityArea;
import br.com.senac.entity.DevStage;
import br.com.senac.entity.Project;
import br.com.senac.entity.User;
import br.com.senac.entity.enums.UserRole;
import br.com.senac.exception.ElementNotFoundException;
import br.com.senac.exception.ForbiddenException;
import br.com.senac.repositories.ActivityAreaRepository;
import br.com.senac.repositories.DevStageRepository;
import br.com.senac.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository repository;
    private final ActivityAreaRepository areaRepository;
    private final DevStageRepository devStageRepository;
    private final AuthService authService;

    public ProjectService(ProjectRepository repository, ActivityAreaRepository areaRepository, DevStageRepository devStageRepository, AuthService authService) {
        this.repository = repository;
        this.areaRepository = areaRepository;
        this.devStageRepository = devStageRepository;
        this.authService = authService;
    }

    public ProjectResponseDTO findById (Long id) {
        return new ProjectResponseDTO(
                repository
                        .findById(id)
                        .orElseThrow(
                                () -> new ElementNotFoundException("Projeto não encontrado")
                        )
        );
    }

    public List<ProjectResponseDTO> findAll(){
        return repository.findAll().stream().map(ProjectResponseDTO::new).toList();
    }

    public ProjectResponseDTO create(ProjectRequestDTO dto){
        DevStage devStage = devStageRepository.findById(dto.devStageId())
                .orElseThrow(() -> new ElementNotFoundException("Estagio de desenvolvimento não existe!"));
        ActivityArea activityArea = areaRepository.findById(dto.activityAreaId())
                .orElseThrow(() -> new ElementNotFoundException("Area de desenvolvimento não existe!"));

        Project projectPersist = new Project();

        projectPersist.setTitle(dto.title());
        projectPersist.setDescription(dto.description());
        projectPersist.setFundGoal(dto.fundGoal());
        projectPersist.setCurrentFund(dto.currentFund());
        projectPersist.setMembers(dto.members());
        projectPersist.setDevStage(devStage);
        projectPersist.setActivityArea(activityArea);
        projectPersist.setImageUrl(dto.imageUrl());
        projectPersist.setOwner(authService.getAuthenticatedUser());

        return new ProjectResponseDTO(repository.save(projectPersist));
    }

    public ProjectResponseDTO update(Long id, ProjectRequestDTO dto) {

        DevStage devStage = devStageRepository.findById(dto.devStageId())
                .orElseThrow(() -> new ElementNotFoundException("Estagio de desenvolvimento não existe!"));
        ActivityArea activityArea = areaRepository.findById(dto.activityAreaId())
                .orElseThrow(() -> new ElementNotFoundException("Area de desenvolvimento não existe!"));

        Project project = repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Projeto informado não existe!"));

        User currentUser = authService.getAuthenticatedUser();
        boolean isOwner = project.getOwner().getId().equals(currentUser.getId());
        boolean isAdmin = currentUser.getRole().equals(UserRole.ADMIN);

        if (!isOwner && !isAdmin) {
            throw new ForbiddenException("Você não pode fazer essa ação!");
        }

        project.setTitle(dto.title());
        project.setDescription(dto.description());
        project.setFundGoal(dto.fundGoal());
        project.setCurrentFund(dto.currentFund());
        project.setMembers(dto.members());
        project.setDevStage(devStage);
        project.setActivityArea(activityArea);
        project.setImageUrl(dto.imageUrl());

        return new ProjectResponseDTO(repository.save(project));

    }

    public void delete(Long id) {
        Project project = repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Projeto informado não existe!"));

        User currentUser = authService.getAuthenticatedUser();
        boolean isOwner = project.getOwner().getId().equals(currentUser.getId());
        boolean isAdmin = currentUser.getRole().equals(UserRole.ADMIN);

        if (!isOwner && !isAdmin) {
            throw new ForbiddenException("Você não pode fazer essa ação!");
        }

        repository.deleteById(id);
    }

    public List<UserProjectResponseDTO> listByUser() {
        User currentUser = authService.getAuthenticatedUser();
        return repository.findByOwner(currentUser).stream().map(UserProjectResponseDTO::new).toList();
    }
}
