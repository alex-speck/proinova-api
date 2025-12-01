package br.com.senac.services;

import br.com.senac.dto.UserLoginDTO;
import br.com.senac.dto.UserRegisterDTO;
import br.com.senac.dto.TokenResponseDTO;
import br.com.senac.entity.User;
import br.com.senac.entity.enums.UserRole;
import br.com.senac.exception.PasswordsDontMatchException;
import br.com.senac.exception.UserAlreadyExistsException;
import br.com.senac.jwt.TokenService;
import br.com.senac.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder encoder,
                       TokenService tokenService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    public TokenResponseDTO login(UserLoginDTO dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
        );

        String token = tokenService.gerarToken(dto.email());

        return new TokenResponseDTO(token);
    }


    public TokenResponseDTO register(UserRegisterDTO dto) {

        if (userRepository.findByUsername(dto.username()).isPresent() || userRepository.findByEmail(dto.email()).isPresent()) throw new UserAlreadyExistsException("Já existe um usuario com esse email e/ou nome de usuário!");
        if (!dto.password().equals(dto.confirmPassword())) throw new PasswordsDontMatchException("As senhas precisam ser iguais!");

        User user = new User();
        user.setEmail(dto.email());
        user.setUsername(dto.username());
        user.setName(dto.name());
        user.setRole(UserRole.PUBLIC);
        user.setPassword(encoder.encode(dto.password()));

        userRepository.save(user);

        return new TokenResponseDTO(tokenService.gerarToken(user.getEmail()));
    }
}
