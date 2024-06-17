package com.example.flightticketmanagement.repositories;

import com.example.flightticketmanagement.models.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    // Finds an administrator by their username
    Optional<Administrator> findByUsername(String username);
}