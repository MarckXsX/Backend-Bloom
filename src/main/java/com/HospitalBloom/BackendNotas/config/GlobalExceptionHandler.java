package com.HospitalBloom.BackendNotas.config;

import com.HospitalBloom.BackendNotas.dto.GenericResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
		String errorMessage = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
		return ResponseEntity.badRequest()
				.body(new GenericResponseDto<>(null, errorMessage, HttpStatus.BAD_REQUEST.value()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGeneralException(Exception ex) {
		String errorMessage = ex.getMessage();
		return ResponseEntity.internalServerError()
				.body(new GenericResponseDto<>(null, errorMessage, HttpStatus.INTERNAL_SERVER_ERROR.value()));
	}
}
