package org.example.Entities;

import jakarta.persistence.*;
import javax.management.relation.Role;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.springframework.security.crypto.password.PasswordEncoder;

public class User {

    @Entity
    @Table(name = "users")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class user {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column (nullable = false, unique = true)
        private String username;

        @Column (nullable = false)
        private String password;
        public String getPassword(){
            if (this.password == null || this.password.trim().isEmpty()) {
                throw new IllegalStateException("Password cannot be null or empty");
            }
            return this.password;
        }

        public boolean checkPassword(String rawPassword, PasswordEncoder passwordEncoder){
            return passwordEncoder.matches(rawPassword, this.password);
        }

        @Column (nullable = false)
        private String email;

        @Column (name = "profile-image")
        private String profileimage;

        @Enumerated (EnumType.STRING)
        private Role role;

        @OneToMany (mappedBy = "user")
        private List <Reservation> reservations;


    }



}
