package com.HospitalBloom.BackendNotas.repositories;

import com.HospitalBloom.BackendNotas.entities.Role;
import com.HospitalBloom.BackendNotas.enums.RoleList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByName(RoleList name);
}
