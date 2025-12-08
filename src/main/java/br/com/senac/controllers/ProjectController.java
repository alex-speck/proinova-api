package br.com.senac.controllers;

import br.com.senac.dto.ProjectRequestDTO;
import br.com.senac.dto.ProjectResponseDTO;
import br.com.senac.services.ImageUploadService;
import br.com.senac.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ImageUploadService imageService;
    private final ProjectService projectService;

    public ProjectController(ImageUploadService imageService, ProjectService projectService) {
        this.imageService = imageService;
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> findAll(){
        return ResponseEntity.ok(projectService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(projectService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> create(@RequestBody @Valid ProjectRequestDTO dto) {
        ProjectResponseDTO response = projectService.create(dto);

        return ResponseEntity.created(URI.create("/projects/" + response.id())).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> update(@PathVariable("id") Long id, @RequestBody @Valid ProjectRequestDTO dto) {
        ProjectResponseDTO response = projectService.update(id, dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<String> imageUpload(@RequestParam("file")MultipartFile file) {
        String response = imageService.handleProjectImageUpload(file);
        return ResponseEntity.created(URI.create(response)).body(response);
    }
}
