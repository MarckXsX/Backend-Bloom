package com.HospitalBloom.BackendNotas.service;

import com.HospitalBloom.BackendNotas.dto.CambioDiagnosticoDto;
import com.HospitalBloom.BackendNotas.dto.CambioDiagnosticoResponseDto;
import com.HospitalBloom.BackendNotas.dto.GenericResponseDto;

import java.util.Optional;

public interface CambioDiagnosticoService {

	GenericResponseDto<?> create(CambioDiagnosticoDto request);

	GenericResponseDto<?> getAll();

	GenericResponseDto<?> get(Long id);

	GenericResponseDto<?>  delete(Long id);

	GenericResponseDto<?> update(Long id, CambioDiagnosticoDto request);
}
