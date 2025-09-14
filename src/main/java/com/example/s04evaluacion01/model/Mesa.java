package com.example.s04evaluacion01.model;

public class Mesa {
    private int idMesa;
    private int numero;
    private int capacidad;
    private String ubicacion;
    private String estado; // disponible, ocupada, reservada, fuera_de_servicio

    public Mesa() {}

    // Para crear nueva mesa (sin id)
    public Mesa(int numero, int capacidad, String ubicacion, String estado) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.estado = estado;
    }

    // Para cargas desde BD (con id)
    public Mesa(int idMesa, int numero, int capacidad, String ubicacion, String estado) {
        this.idMesa = idMesa;
        this.numero = numero;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.estado = estado;
    }

    public int getIdMesa() { return idMesa; }
    public void setIdMesa(int idMesa) { this.idMesa = idMesa; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Mesa{idMesa=" + idMesa + ", numero=" + numero + ", capacidad=" + capacidad +
                ", ubicacion='" + ubicacion + '\'' + ", estado='" + estado + '\'' + '}';
    }
}
