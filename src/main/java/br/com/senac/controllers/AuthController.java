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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> me(Authentication authentication) {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        var user = userDetails.getUser();

        return ResponseEntity.ok(
                new UserResponseDTO(user)
        );
    }
}
