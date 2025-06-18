package com.HospitalBloom.BackendNotas.service;

import com.HospitalBloom.BackendNotas.dto.NewUserDto;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

	public String authenticate(String username, String password, HttpServletResponse response);

	public void registerUser(NewUserDto newUserDto);

	public void logout(HttpServletResponse response);

}
