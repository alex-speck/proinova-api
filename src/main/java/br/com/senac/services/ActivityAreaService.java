package br.com.senac.services;

import br.com.senac.dto.ActivityAreaRequestDTO;
import br.com.senac.dto.ActivityAreaResponseDTO;
import br.com.senac.entity.ActivityArea;
import br.com.senac.entity.DevStage;
import br.com.senac.exception.ElementNotFoundException;
import br.com.senac.exception.UndeletableElementException;
import br.com.senac.repositories.ActivityAreaRepository;
import br.com.senac.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityAreaService {

    private final ActivityAreaRepository repository;
    private final ProjectRepository projectRepository;

    public ActivityAreaService(ActivityAreaRepository repository, ProjectRepository projectRepository) {
        this.repository = repository;
        this.projectRepository = projectRepository;
    }

    public ActivityAreaResponseDTO create(ActivityAreaRequestDTO requestDTO) {
        ActivityArea activityArea = new ActivityArea();
        activityArea.setArea(requestDTO.area());
        return new ActivityAreaResponseDTO(repository.save(activityArea));
    }

    public ActivityAreaResponseDTO update(Long id, ActivityAreaRequestDTO area) {
        if (repository.findById(id).isEmpty()) {
            throw new ElementNotFoundException("Area de desenvolvimento com id " + id + " não existe!");
        }
        ActivityArea activityArea = new ActivityArea();
        activityArea.setArea(area.area());
        activityArea.setId(id);
        return new ActivityAreaResponseDTO(repository.save(activityArea));
    }

    public List<ActivityAreaResponseDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(ActivityAreaResponseDTO::new)
                .toList();
    }

    public void delete(Long id){
        try{
            if (projectRepository.existsByActivityAreaId(id)){
                throw new UndeletableElementException("Area de desenvolvimento esta em uso por um ou mais projetos");
            }
            repository.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }


    public List<ActivityArea> adminFindAll(){
        return repository.findAll();
    }

}
