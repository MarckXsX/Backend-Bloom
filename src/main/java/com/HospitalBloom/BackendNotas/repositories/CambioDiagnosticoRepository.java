package com.HospitalBloom.BackendNotas.repositories;

import com.HospitalBloom.BackendNotas.entities.CambioDiagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CambioDiagnosticoRepository extends JpaRepository<CambioDiagnostico,Long> {
}
