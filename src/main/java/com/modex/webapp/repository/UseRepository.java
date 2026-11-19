package com.modex.webapp.repository;

import com.modex.webapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user using their email
    Optional<User> findByEmail(String email);

    // Check whether an email is already registered
    boolean existsByEmail(String email);

    // Find users by their assigned role
    List<User> findByRole(String role);

    // Find users by name
    List<User> findByNameContainingIgnoreCase(String name);

    // Find users by role and email
    Optional<User> findByEmailAndRole(String email, String role);

    // Delete a user using their email
    void deleteByEmail(String email);
}

