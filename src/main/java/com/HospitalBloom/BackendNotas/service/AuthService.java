package com.HospitalBloom.BackendNotas.service;

import com.HospitalBloom.BackendNotas.dto.NewUserDto;

public interface AuthService {

    public String authenticate(String username, String password);
    public void registerUser(NewUserDto newUserDto);

}
