package com.modex.webapp.repository;

import com.modex.webapp.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    // Find organization by name
    Optional<Organization> findByName(String name);

    // Check whether organization already exists
    boolean existsByName(String name);

    // Find organizations by name (partial match)
    List<Organization> findByNameContainingIgnoreCase(String name);

}
