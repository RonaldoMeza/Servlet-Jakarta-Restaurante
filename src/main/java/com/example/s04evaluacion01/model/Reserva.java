package com.example.s04evaluacion01.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Reserva {
    private int idReserva;
    private Integer idCliente; // nullable
    private Integer idMesa;    // nullable
    private Date fechaReserva;
    private Time hora;
    private int numPersonas;
    private String estado; // pendiente, confirmada, cancelada, asistio
    private String observaciones;
    private Timestamp createdAt;

    // campos auxiliares (no persisten en BD): opcionales para mostrar en vistas
    private String clienteNombre;
    private Integer mesaNumero;

    public Reserva() {}

    // Constructor para crear nueva reserva (sin id ni createdAt)
    public Reserva(Integer idCliente, Integer idMesa, Date fechaReserva, Time hora, int numPersonas, String estado, String observaciones) {
        this.idCliente = idCliente;
        this.idMesa = idMesa;
        this.fechaReserva = fechaReserva;
        this.hora = hora;
        this.numPersonas = numPersonas;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    // Constructor completo
    public Reserva(int idReserva, Integer idCliente, Integer idMesa, Date fechaReserva, Time hora, int numPersonas, String estado, String observaciones, Timestamp createdAt) {
        this.idReserva = idReserva;
        this.idCliente = idCliente;
        this.idMesa = idMesa;
        this.fechaReserva = fechaReserva;
        this.hora = hora;
        this.numPersonas = numPersonas;
        this.estado = estado;
        this.observaciones = observaciones;
        this.createdAt = createdAt;
    }

    public int getIdReserva() { return idReserva; }
    public void setIdReserva(int idReserva) { this.idReserva = idReserva; }

    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }

    public Integer getIdMesa() { return idMesa; }
    public void setIdMesa(Integer idMesa) { this.idMesa = idMesa; }

    public Date getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(Date fechaReserva) { this.fechaReserva = fechaReserva; }

    public Time getHora() { return hora; }
    public void setHora(Time hora) { this.hora = hora; }

    public int getNumPersonas() { return numPersonas; }
    public void setNumPersonas(int numPersonas) { this.numPersonas = numPersonas; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }

    public Integer getMesaNumero() { return mesaNumero; }
    public void setMesaNumero(Integer mesaNumero) { this.mesaNumero = mesaNumero; }

    @Override
    public String toString() {
        return "Reserva{idReserva=" + idReserva + ", idCliente=" + idCliente + ", idMesa=" + idMesa +
                ", fechaReserva=" + fechaReserva + ", hora=" + hora + ", numPersonas=" + numPersonas +
                ", estado='" + estado + '\'' + ", observaciones='" + observaciones + '\'' + ", createdAt=" + createdAt + '}';
    }
}