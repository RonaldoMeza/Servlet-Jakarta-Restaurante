package com.example.s04evaluacion01.model;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String telefono;
    private String email;
    private String dni;

    public Cliente() {}

    public Cliente(String nombre, String telefono, String email, String dni) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.dni = dni;
    }

    public Cliente(int idCliente, String nombre, String telefono, String email, String dni) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.dni = dni;
    }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    @Override
    public String toString() {
        return "Cliente{idCliente=" + idCliente + ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' + ", email='" + email + '\'' +
                ", dni='" + dni + '\'' + '}';
    }
}