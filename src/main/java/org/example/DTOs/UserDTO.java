package org.example.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.example.Enums.Role;

@Data
@Builder
public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String profileimage;
    private Role role;

    @Data
    @Builder

    public static class UpdateDTO {

        private String firstName;
        private String lastName;
        private String email;
        private String profileImage;
    }

    @Data
    @Builder

    public static class RegistrationDTO {

        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 50)
        private String username;

        @NotBlank(message = "Password is required")
        @Size(min = 6)
        private String password;

        @NotBlank(message = "Email is required")
        @Email
        private String email;

        private String firstName;
        private String lastName;
    }
}
