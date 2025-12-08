package br.com.senac.controllers;

import br.com.senac.dto.ActivityAreaRequestDTO;
import br.com.senac.dto.ActivityAreaResponseDTO;
import br.com.senac.dto.DevStageRequestDTO;
import br.com.senac.dto.DevStageResponseDTO;
import br.com.senac.services.ActivityAreaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/activityarea")
public class ActivityAreaController {

    private final ActivityAreaService service;

    public ActivityAreaController(ActivityAreaService service) {
        this.service = service;
    }
    // ROTA DEVE SER APENAS PARA ADMINS
    @PostMapping
    public ResponseEntity<ActivityAreaResponseDTO> create(@RequestBody @Valid ActivityAreaRequestDTO dto){
        ActivityAreaResponseDTO responseDTO = service.create(dto);
        return ResponseEntity
                .created(URI.create("/activityarea/" + responseDTO.id().toString()))
                .body(responseDTO);
    }
    // ROTA DEVE SER APENAS PARA ADMINS
    @PutMapping("/{id}")
    public ResponseEntity<ActivityAreaResponseDTO> update(@PathVariable("id") Long id, @RequestBody @Valid ActivityAreaRequestDTO dto){
        ActivityAreaResponseDTO responseDTO = service.update(id, dto);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ActivityAreaResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // ROTA DEVE SER APENAS PARA ADMINS
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
