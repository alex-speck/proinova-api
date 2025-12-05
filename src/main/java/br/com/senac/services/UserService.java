package br.com.senac.services;

import br.com.senac.dto.UserNameUpdateRequestDTO;
import br.com.senac.dto.UserPasswordUpdateRequestDTO;
import br.com.senac.dto.UserProjectResponseDTO;
import br.com.senac.dto.UserResponseDTO;
import br.com.senac.entity.User;
import br.com.senac.exception.PasswordsDontMatchException;
import br.com.senac.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final AuthService authService;
    private final ProjectService projectService;

    public UserService(UserRepository repository, PasswordEncoder encoder, AuthService authService, ProjectService projectService) {
        this.repository = repository;
        this.encoder = encoder;
        this.authService = authService;
        this.projectService = projectService;
    }

    public UserResponseDTO getUserInfo() {
        User user = authService.getAuthenticatedUser();
        return new UserResponseDTO(user);
    }

    public UserResponseDTO updateUserName(UserNameUpdateRequestDTO dto) {
        User currentUser = authService.getAuthenticatedUser();

        if ((dto.name() != null) && (dto.name().trim() != "")) {
            currentUser.setName(dto.name().trim());
        }

        if ((dto.username() != null) && (dto.username().trim() != "")) {
            currentUser.setUsername(dto.username().trim());
        }

        return new UserResponseDTO(repository.save(currentUser));
    }

    public UserResponseDTO updatePassword(UserPasswordUpdateRequestDTO dto) {
        User currentUser = authService.getAuthenticatedUser();
        if (!encoder.matches(dto.currentPass(), currentUser.getPassword())) {
            throw new PasswordsDontMatchException("Senha informada é invalida!");
        }

        if (!dto.newPass().equals(dto.confirmPass())){
            throw new PasswordsDontMatchException("As senhas não conhecidem!");
        }

        if (encoder.matches(dto.newPass(), currentUser.getPassword())) {
            throw new PasswordsDontMatchException("A nova senha não pode ser igual à atual.");
        }

        currentUser.setPassword(encoder.encode(dto.newPass()));
        return new UserResponseDTO(repository.save(currentUser));
    }

    public List<UserProjectResponseDTO> findProjects() {
        return projectService.listByUser();
    }
}
