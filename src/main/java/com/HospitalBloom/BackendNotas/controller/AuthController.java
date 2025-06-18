package com.HospitalBloom.BackendNotas.controller;

import com.HospitalBloom.BackendNotas.dto.GenericResponseDto;
import com.HospitalBloom.BackendNotas.dto.LoginUserDto;
import com.HospitalBloom.BackendNotas.dto.NewUserDto;
import com.HospitalBloom.BackendNotas.entities.User;
import com.HospitalBloom.BackendNotas.service.AuthService;
import com.HospitalBloom.BackendNotas.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginUserDto loginUserDto, BindingResult bindingResult,
			HttpServletResponse response) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest()
					.body(new GenericResponseDto<>(null, "Revise sus credenciales", HttpStatus.BAD_REQUEST.value()));
		}
		try {
			String roleName = authService.authenticate(loginUserDto.getUserName(), loginUserDto.getPassword(),
					response);
			if(roleName != null){
				return ResponseEntity.ok(new GenericResponseDto<>(roleName, "Rol de Usuario", HttpStatus.OK.value()));
			}else {
				return ResponseEntity.badRequest()
						.body(new GenericResponseDto<>(null, "No autorizado", HttpStatus.BAD_REQUEST.value()));
			}
		} catch (Exception e) {
			System.out.println("Exception = " + e.getMessage());
			return ResponseEntity.badRequest()
					.body(new GenericResponseDto<>(null, e.getMessage(), HttpStatus.BAD_REQUEST.value()));
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody NewUserDto newUserDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest()
					.body(new GenericResponseDto<>(null, "Revise los campos", HttpStatus.BAD_REQUEST.value()));
		}
		try {
			authService.registerUser(newUserDto);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new GenericResponseDto<>(null, "Registrado", HttpStatus.CREATED.value()));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest()
					.body(new GenericResponseDto<>(null, e.getMessage(), HttpStatus.BAD_REQUEST.value()));
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletResponse response){
		authService.logout(response);
		return ResponseEntity.ok(new GenericResponseDto<>(null,"Sesion finalizada",HttpStatus.OK.value()));
	}
}
