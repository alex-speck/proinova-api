package br.com.senac.controllers;

import br.com.senac.dto.*;
import br.com.senac.services.ImageUploadService;
import br.com.senac.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    private final ImageUploadService uploadService;

    public UserController(UserService service, ImageUploadService uploadService) {
        this.service = service;
        this.uploadService = uploadService;
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

    @GetMapping
    public ResponseEntity<UserResponseDTO> userInfo(){
        return ResponseEntity.ok(service.getUserInfo());
    }

    @PutMapping("/update/name")
    public ResponseEntity<UserResponseDTO> updateUserName (@RequestBody @Valid UserNameUpdateRequestDTO dto){
        return ResponseEntity.ok(service.updateUserName(dto));
    }

    @PutMapping("/update/password")
    public ResponseEntity<UserResponseDTO> updatePassword (@RequestBody @Valid UserPasswordUpdateRequestDTO dto){
        return ResponseEntity.ok(service.updatePassword(dto));
    }

    @PutMapping("/update/profile-picture")
    public ResponseEntity<UserResponseDTO> updateProfilePicture (@RequestBody @Valid UserPictureUpdateRequestDTO dto){
        return ResponseEntity.ok(service.updateProfilePicture(dto));
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("file")MultipartFile file) {
        return ResponseEntity.ok(uploadService.handleProfilePictureUpload(file));
    }

}
