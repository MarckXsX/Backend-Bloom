package com.HospitalBloom.BackendNotas.service.impl;

import com.HospitalBloom.BackendNotas.dto.CambioDiagnosticoDto;
import com.HospitalBloom.BackendNotas.dto.CambioDiagnosticoResponseDto;
import com.HospitalBloom.BackendNotas.dto.GenericResponseDto;
import com.HospitalBloom.BackendNotas.entities.CambioDiagnostico;
import com.HospitalBloom.BackendNotas.enums.StatusDiagnostico;
import com.HospitalBloom.BackendNotas.repositories.CambioDiagnosticoRepository;
import com.HospitalBloom.BackendNotas.service.CambioDiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CambioDiagnosticoServiceImpl implements CambioDiagnosticoService {

	@Autowired
	private CambioDiagnosticoRepository cambioDiagnosticoRepository;

	@Autowired
	private UserServiceImpl userService;

	@Override
	public GenericResponseDto<?> create(CambioDiagnosticoDto request) {

		CambioDiagnostico diagnostico = getCambioDiagnostico(request);

		CambioDiagnostico saveDiagnostico = cambioDiagnosticoRepository.save(diagnostico);

		CambioDiagnosticoResponseDto responseDto = getCambioDiagnosticoResponseDto(saveDiagnostico);

		return GenericResponseDto.<CambioDiagnosticoResponseDto>builder().data(responseDto).message("Datos guardados")
				.status(HttpStatus.OK.value()).build();
	}

	@Override
	public GenericResponseDto<?> getAll() {
		List<CambioDiagnostico> Lista = cambioDiagnosticoRepository.findAll();
		if (Lista.isEmpty()) {
			return GenericResponseDto.<List<CambioDiagnostico>>builder().data(null).message("Datos no encontrados")
					.status(HttpStatus.NOT_FOUND.value()).build();
		}
		return GenericResponseDto.<List<CambioDiagnostico>>builder().data(Lista).message("Datos obtenidos")
				.status(HttpStatus.OK.value()).build();
	}

	@Override
	public GenericResponseDto<?> get(Long id) {
		Optional<CambioDiagnostico> diagnostico = cambioDiagnosticoRepository.findById(id);
		if (diagnostico.isPresent()) {
			CambioDiagnosticoResponseDto responseDto = getCambioDiagnosticoResponseDto(diagnostico.orElseThrow());
			return GenericResponseDto.<CambioDiagnosticoResponseDto>builder().data(responseDto)
					.message("Datos obtenidos").status(HttpStatus.OK.value()).build();
		}
		return GenericResponseDto.<CambioDiagnosticoResponseDto>builder().data(null).message("Datos no encontrados")
				.status(HttpStatus.NOT_FOUND.value()).build();
	}

	@Override
	public GenericResponseDto<?> delete(Long id) {
		cambioDiagnosticoRepository.deleteById(id);
		return GenericResponseDto.<CambioDiagnosticoResponseDto>builder().data(null).message("Diagnostico Eliminado")
				.status(HttpStatus.OK.value()).build();
	}

	@Override
	public GenericResponseDto<?> update(Long id, CambioDiagnosticoDto request) {
		Optional<CambioDiagnostico> diagnosticoOptional = cambioDiagnosticoRepository.findById(id);
		if (diagnosticoOptional.isPresent()) {
			
			CambioDiagnostico diagnostico = diagnosticoOptional.orElseThrow();
			diagnostico.setNumExpediente(request.getNumExpediente());
			diagnostico.setServicio(request.getServicio());
			diagnostico.setFechaAlta(request.getFechaAlta());
			diagnostico.setDiagnosticoEgreso(request.getDiagnosticoEgreso());
			diagnostico.setDiagnosticoCambiado(request.getDiagnosticoCambiado());
			diagnostico.setMotivoNoCambio(request.getMotivoNoCambio());
			diagnostico.setFechaNotificacion(request.getFechaNotificacion());
			diagnostico.setUser(userService.existsById(request.getIdUser()));
			diagnostico.setEstatus(request.getEstatus());

			CambioDiagnosticoResponseDto responseDto = getCambioDiagnosticoResponseDto(cambioDiagnosticoRepository.save(diagnostico));
			return GenericResponseDto.<CambioDiagnosticoResponseDto>builder().data(responseDto)
					.message("Datos actualizados del diagnostico").status(HttpStatus.OK.value()).build();
		}
		return GenericResponseDto.<CambioDiagnosticoResponseDto>builder().data(null).message("Error al actualizar el Diagnostico")
				.status(HttpStatus.NOT_FOUND.value()).build();
	}

	private  CambioDiagnostico getCambioDiagnostico(CambioDiagnosticoDto request) {
		CambioDiagnostico diagnostico = new CambioDiagnostico();
		diagnostico.setNumExpediente(request.getNumExpediente());
		diagnostico.setServicio(request.getServicio());
		diagnostico.setFechaAlta(request.getFechaAlta());
		diagnostico.setDiagnosticoEgreso(request.getDiagnosticoEgreso());
		diagnostico.setDiagnosticoCambiado(request.getDiagnosticoCambiado());
		diagnostico.setMotivoNoCambio(request.getMotivoNoCambio());
		diagnostico.setFechaNotificacion(request.getFechaNotificacion());
		diagnostico.setUser(userService.existsById(request.getIdUser()));
		diagnostico.setEstatus(StatusDiagnostico.PENDIENTE);
		return diagnostico;
	}

	private  CambioDiagnosticoResponseDto getCambioDiagnosticoResponseDto(CambioDiagnostico saveDiagnostico) {
		CambioDiagnosticoResponseDto responseDto = new CambioDiagnosticoResponseDto();
		responseDto.setId(saveDiagnostico.getId());
		responseDto.setNumExpediente(saveDiagnostico.getNumExpediente());
		responseDto.setServicio(saveDiagnostico.getServicio());
		responseDto.setFechaAlta(saveDiagnostico.getFechaAlta());
		responseDto.setDiagnosticoEgreso(saveDiagnostico.getDiagnosticoEgreso());
		responseDto.setDiagnosticoCambiado(saveDiagnostico.getDiagnosticoCambiado());
		responseDto.setMotivoNoCambio(saveDiagnostico.getMotivoNoCambio());
		responseDto.setFechaNotificacion(saveDiagnostico.getFechaNotificacion());
		responseDto.setIdUser(saveDiagnostico.getUser().getId());
		responseDto.setEstatus(saveDiagnostico.getEstatus());
		return responseDto;
	}
}
