package br.com.senac.controllers;

import br.com.senac.dto.UserLoginDTO;
import br.com.senac.dto.UserRegisterDTO;
import br.com.senac.dto.TokenResponseDTO;
import br.com.senac.dto.UserResponseDTO;
import br.com.senac.entity.User;
import br.com.senac.jwt.UserDetailsImpl;
import br.com.senac.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid UserLoginDTO dto){
        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<TokenResponseDTO> register(@RequestBody @Valid UserRegisterDTO dto){
        return ResponseEntity.ok(authService.register(dto));
    }

}
