package com.HospitalBloom.BackendNotas.controller;

import com.HospitalBloom.BackendNotas.entities.Cie10;
import com.HospitalBloom.BackendNotas.service.impl.Cie10ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Api/v1/Cie10")
public class Cie10Controller {

    @Autowired
    private Cie10ServiceImpl cie10Service;

    @GetMapping("/codigo")
    public List<Cie10> buscar(@RequestParam("query") String query) {
        return cie10Service.buscarPorCodigo(query);
    }
}
