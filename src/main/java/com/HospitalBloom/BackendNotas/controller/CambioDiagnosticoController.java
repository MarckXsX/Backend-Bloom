package com.HospitalBloom.BackendNotas.controller;

import com.HospitalBloom.BackendNotas.dto.CambioDiagnosticoDto;
import com.HospitalBloom.BackendNotas.dto.CambioDiagnosticoResponseDto;
import com.HospitalBloom.BackendNotas.dto.GenericResponseDto;
import com.HospitalBloom.BackendNotas.service.CambioDiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Api/v1/Diagnosticos")
public class CambioDiagnosticoController {

    @Autowired
    private CambioDiagnosticoService cambioDiagnosticoService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CambioDiagnosticoDto request){
        GenericResponseDto<?> response = cambioDiagnosticoService.create(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        GenericResponseDto<?> response = cambioDiagnosticoService.getAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        GenericResponseDto<?> response = cambioDiagnosticoService.get(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
