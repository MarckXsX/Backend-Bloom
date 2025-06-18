package com.HospitalBloom.BackendNotas.service;

import com.HospitalBloom.BackendNotas.entities.Cie10;

import java.util.List;

public interface Cie10Service {

    List<Cie10> buscarPorCodigo(String codigo);
}
