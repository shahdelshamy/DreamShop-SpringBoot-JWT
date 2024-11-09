package com.global.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.global.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByName(String role);

}
