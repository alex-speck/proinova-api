package br.com.senac.controllers;

import br.com.senac.dto.DevStageRequestDTO;
import br.com.senac.dto.DevStageResponseDTO;
import br.com.senac.services.DevStageService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/devstage")
public class DevStageController {

    private final DevStageService service;

    public DevStageController(DevStageService service) {
        this.service = service;
    }
    // ROTA DEVE SER APENAS PARA ADMINS
    @PostMapping
    public ResponseEntity<DevStageResponseDTO> create(@RequestBody @Valid DevStageRequestDTO dto){
        DevStageResponseDTO responseDTO = service.create(dto);
        return ResponseEntity
                .created(URI.create("/devstage/" + responseDTO.id().toString()))
                .body(responseDTO);
    }
    // ROTA DEVE SER APENAS PARA ADMINS
    @PutMapping("/{id}")
    public ResponseEntity<DevStageResponseDTO> update(@PathVariable("id") Long id, @RequestBody @Valid DevStageRequestDTO dto){
        DevStageResponseDTO responseDTO = service.update(id, dto);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<DevStageResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // ROTA DEVE SER APENAS PARA ADMINS
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
