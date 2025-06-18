package com.HospitalBloom.BackendNotas.entities;

import com.HospitalBloom.BackendNotas.enums.StatusDiagnostico;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name = "CambioDiagnostico")
public class CambioDiagnostico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String numExpediente;
	private String servicio;
	private LocalDate fechaAlta;
	private String diagnosticoEgreso;
	private String diagnosticoCambiado;
	private String motivoNoCambio;
	private LocalDate fechaNotificacion;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private StatusDiagnostico estatus;

}
