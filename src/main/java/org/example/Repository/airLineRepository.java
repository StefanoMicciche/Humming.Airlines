package org.example.Repository;

import org.example.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Repository

public interface airLineRepository extends JpaRepository <User.user, Long>{
    Optional <User.user> findByUserName(String username);
    Optional <User.user> findByEmail (String email);

    boolean existsByUserName (String username);
    boolean existsByEmail (String email);

    @Query ("SELECT u FROM u WHERE u role = :role")
    List <User.user> findByRole (@Param("role")Role role);
}

