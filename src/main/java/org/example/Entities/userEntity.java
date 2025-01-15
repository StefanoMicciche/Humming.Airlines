package org.example.Entities;

import jakarta.persistence.*;

import javax.management.relation.Role;
import java.util.List;

public class userEntity {

    @Entity
    @Table(name = "users")

    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column (nullable = false, unique = true)
        private String username;

        @Column (nullable = false)
        private String password;

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
