package com.HospitalBloom.BackendNotas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "cie10")
public class Cie10 {

    @Id
    private String codigo;
    private String descripcion;
    private Integer sexo;
    private Integer edad_minima;
    private Integer edad_maxima;

}
