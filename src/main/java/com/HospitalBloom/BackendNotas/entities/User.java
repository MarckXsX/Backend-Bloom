package com.HospitalBloom.BackendNotas.entities;

import com.HospitalBloom.BackendNotas.enums.StatusUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(unique = true, nullable = false)
	private String userName;

	@JsonIgnore
	@NotBlank
	@Column(nullable = false)
	private String password;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CambioDiagnostico> cambioDiagnosticoList;

	private StatusUser statusUser;

	public User(String userName, String password, Role role, StatusUser statusUser) {
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.statusUser = statusUser;
	}
}
