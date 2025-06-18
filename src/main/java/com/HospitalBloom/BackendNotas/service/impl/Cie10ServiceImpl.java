package com.HospitalBloom.BackendNotas.service.impl;

import com.HospitalBloom.BackendNotas.entities.Cie10;
import com.HospitalBloom.BackendNotas.repositories.Cie10Repository;
import com.HospitalBloom.BackendNotas.service.Cie10Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Cie10ServiceImpl implements Cie10Service {

    @Autowired
    private Cie10Repository repository;

    @Override
    public List<Cie10> buscarPorCodigo(String codigo) {
        return repository.findTop50ByCodigoContainingIgnoreCase(codigo);
    }
}
