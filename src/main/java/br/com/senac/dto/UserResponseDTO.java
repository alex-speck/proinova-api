package br.com.senac.dto;

import br.com.senac.entity.User;
import br.com.senac.entity.enums.UserRole;

public record UserResponseDTO(Long id, String email, String username, String name, UserRole role, String userProfilePictureUrl) {
    public UserResponseDTO(User user){
        this(user.getId(), user.getEmail(), user.getUsername(), user.getName(), user.getRole(), user.getProfilePictureURL());
    }
}
