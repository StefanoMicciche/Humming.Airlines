
package org.example.Services;


import lombok.RequiredArgsConstructor;
import org.example.DTOs.UserDTO;
import org.example.Entities.User;
import org.example.Enums.Role;
import org.example.ExceptionHandler.User.DuplicateUserException;
import org.example.Repository.FlightRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final FlightRepository.ReservationRepository.UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public org.example.Entities.User createUser(org.example.DTOs.UserDTO userDTO) {
        if (userRepository.existByUserName(userDTO.getUsername())) {
            throw new org.example.ExceptionHandler.User.DuplicateUserException("Username already exists: " + userDTO.getUsername());
        }

        if (userRepository.existByEmail(userDTO.getEmail())) {
            throw new org.example.ExceptionHandler.User.DuplicateUserException("Email already exists: " + userDTO.getEmail());
        }

        org.example.Entities.User user = org.springframework.security.core.userdetails.User.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .role(org.example.Enums.Role.ROLE_USER)
                .build();

        return userRepository.save(user);
    }
}
