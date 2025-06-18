package com.HospitalBloom.BackendNotas.repositories;

import com.HospitalBloom.BackendNotas.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserName(String userName);

	List<User> findByRole_Id(Integer id);

	boolean existsByUserName(String userName);
}
