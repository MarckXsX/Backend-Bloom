package com.HospitalBloom.BackendNotas.enums;

public enum StatusUser {
    PENDIENTE("PENDIENTE", 0), APROBADO("APROBADO", 1);

    private String name;
    private int num;

    private StatusUser(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }
}
