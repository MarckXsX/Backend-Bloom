package com.HospitalBloom.BackendNotas.dto;

import com.HospitalBloom.BackendNotas.enums.StatusDiagnostico;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CambioDiagnosticoDto {

	private String numExpediente;
	private String servicio;
	private LocalDate fechaAlta;
	private String diagnosticoEgreso;
	private String diagnosticoCambiado;
	private String motivoNoCambio;
	private LocalDate fechaNotificacion;
	private Long idUser;
	private StatusDiagnostico estatus;
}
