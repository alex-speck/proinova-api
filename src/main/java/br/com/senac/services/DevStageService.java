package br.com.senac.services;

import br.com.senac.dto.DevStageRequestDTO;
import br.com.senac.dto.DevStageResponseDTO;
import br.com.senac.entity.DevStage;
import br.com.senac.exception.ElementNotFoundException;
import br.com.senac.exception.UndeletableElementException;
import br.com.senac.repositories.DevStageRepository;
import br.com.senac.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevStageService {

    private final DevStageRepository repository;
    private final ProjectRepository projectRepository;


    public DevStageService(DevStageRepository repository, ProjectRepository projectRepository) {
        this.repository = repository;
        this.projectRepository = projectRepository;
    }

    public DevStageResponseDTO create(DevStageRequestDTO requestDTO) {
        DevStage devStage = new DevStage();
        devStage.setStage(requestDTO.stage());
        return new DevStageResponseDTO(repository.save(devStage));
    }

    public DevStageResponseDTO update(Long id, DevStageRequestDTO requestDTO) {
        if (repository.findById(id).isEmpty()) {
            throw new ElementNotFoundException("Estagio de desenvolvimento com id " + id + " não existe!");
        }
        DevStage devStage = new DevStage();
        devStage.setStage(requestDTO.stage());
devStage.setId(id);
        return new DevStageResponseDTO(repository.save(devStage));
    }

    public List<DevStageResponseDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(DevStageResponseDTO::new)
                .toList();
    }

    public void delete(Long id){
        try{
            if (projectRepository.existsByDevStageId(id)){
                throw new UndeletableElementException("Estagio de desenvolvimento esta em uso por um ou mais projetos");
            }
            repository.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    public List<DevStage> adminFindAll(){
        return repository.findAll();
    }
}
