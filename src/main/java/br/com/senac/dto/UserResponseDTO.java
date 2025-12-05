package br.com.senac.dto;

import br.com.senac.entity.User;
import br.com.senac.entity.enums.UserRole;

public record UserResponseDTO(Long id, String email, String username, String name, UserRole role) {
    public UserResponseDTO(User user){
        this(user.getId(), user.getUsername(), user.getEmail(), user.getName(), user.getRole());
    }
}
