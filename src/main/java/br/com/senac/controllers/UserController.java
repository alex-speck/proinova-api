package br.com.senac.controllers;

import br.com.senac.dto.UserNameUpdateRequestDTO;
import br.com.senac.dto.UserPasswordUpdateRequestDTO;
import br.com.senac.dto.UserProjectResponseDTO;
import br.com.senac.dto.UserResponseDTO;
import br.com.senac.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    /*
    Deve conter controle do usuario:
        Atualização de informações como:
            Nome de usuario,
            nome e
            senha

     CADASTRO DE USUARIO AINDA CONTINUA NO /auth/register
     */

    @GetMapping("/projects")
    public ResponseEntity<List<UserProjectResponseDTO>> findUserProjects() {
        return ResponseEntity.ok(service.findProjects());
    }

    @PutMapping("/update-name")
    public ResponseEntity<UserResponseDTO> updateUserName (@RequestBody @Valid UserNameUpdateRequestDTO dto){
        return ResponseEntity.ok(service.updateUserName(dto));
    }

    @PutMapping("/update-password")
    public ResponseEntity<UserResponseDTO> updatePassword (@RequestBody @Valid UserPasswordUpdateRequestDTO dto){
        return ResponseEntity.ok(service.updatePassword(dto));
    }

}
