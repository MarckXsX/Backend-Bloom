package com.HospitalBloom.BackendNotas.repositories;

import com.HospitalBloom.BackendNotas.entities.Cie10;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Cie10Repository  extends JpaRepository<Cie10, String> {

    List<Cie10> findTop50ByCodigoContainingIgnoreCase(String codigo);
}
