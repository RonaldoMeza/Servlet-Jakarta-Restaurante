package com.example.s04evaluacion01.dao;

import com.example.s04evaluacion01.model.Reserva;
import com.example.s04evaluacion01.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    /**
     * Obtiene todas las reservas. Incluye LEFT JOIN para traer nombre del cliente y número de mesa.
     */
    public List<Reserva> getAll() {
        List<Reserva> list = new ArrayList<>();
        String sql = "SELECT r.id_reserva, r.id_cliente, r.id_mesa, r.fecha_reserva, r.hora, r.num_personas, r.estado, r.observaciones, r.created_at, " +
                "c.nombre AS cliente_nombre, m.numero AS mesa_numero " +
                "FROM reserva r " +
                "LEFT JOIN cliente c ON r.id_cliente = c.id_cliente " +
                "LEFT JOIN mesa m ON r.id_mesa = m.id_mesa " +
                "ORDER BY r.fecha_reserva, r.hora";
        try (Connection conn = DBUtil.getConnection()) {
            if (conn == null) return list;
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Reserva r = mapRow(rs);
                    r.setClienteNombre(rs.getString("cliente_nombre"));
                    int mesaNum = rs.getInt("mesa_numero");
                    if (!rs.wasNull()) r.setMesaNumero(mesaNum);
                    list.add(r);
                }
            }
        } catch (SQLException e) {
            System.err.println("[ReservaDAO.getAll] Error SQL:");
            e.printStackTrace();
        }
        return list;
    }

    public Reserva getById(int id) {
        String sql = "SELECT r.id_reserva, r.id_cliente, r.id_mesa, r.fecha_reserva, r.hora, r.num_personas, r.estado, r.observaciones, r.created_at, " +
                "c.nombre AS cliente_nombre, m.numero AS mesa_numero " +
                "FROM reserva r " +
                "LEFT JOIN cliente c ON r.id_cliente = c.id_cliente " +
                "LEFT JOIN mesa m ON r.id_mesa = m.id_mesa " +
                "WHERE r.id_reserva = ?";
        try (Connection conn = DBUtil.getConnection()) {
            if (conn == null) return null;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Reserva r = mapRow(rs);
                        r.setClienteNombre(rs.getString("cliente_nombre"));
                        int mesaNum = rs.getInt("mesa_numero");
                        if (!rs.wasNull()) r.setMesaNumero(mesaNum);
                        return r;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("[ReservaDAO.getById] Error SQL:");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Inserta una reserva. Devuelve id generado o -1 en caso de error.
     */
    public int create(Reserva reserva) {
        String sql = "INSERT INTO reserva (id_cliente, id_mesa, fecha_reserva, hora, num_personas, estado, observaciones) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = DBUtil.getConnection()) {
            if (conn == null) return -1;
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                if (reserva.getIdCliente() != null) ps.setInt(1, reserva.getIdCliente());
                else ps.setNull(1, Types.INTEGER);

                if (reserva.getIdMesa() != null) ps.setInt(2, reserva.getIdMesa());
                else ps.setNull(2, Types.INTEGER);

                ps.setDate(3, reserva.getFechaReserva());
                ps.setTime(4, reserva.getHora());
                ps.setInt(5, reserva.getNumPersonas());
                ps.setString(6, reserva.getEstado());
                ps.setString(7, reserva.getObservaciones());

                int affected = ps.executeUpdate();
                if (affected == 0) {
                    System.err.println("[ReservaDAO.create] No se insertó la reserva.");
                    return -1;
                }
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        int id = keys.getInt(1);
                        reserva.setIdReserva(id);
                        return id;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("[ReservaDAO.create] Error SQL:");
            e.printStackTrace();
        }
        return -1;
    }

    public boolean update(Reserva reserva) {
        String sql = "UPDATE reserva SET id_cliente = ?, id_mesa = ?, fecha_reserva = ?, hora = ?, num_personas = ?, estado = ?, observaciones = ? WHERE id_reserva = ?";
        try (Connection conn = DBUtil.getConnection()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                if (reserva.getIdCliente() != null) ps.setInt(1, reserva.getIdCliente());
                else ps.setNull(1, Types.INTEGER);

                if (reserva.getIdMesa() != null) ps.setInt(2, reserva.getIdMesa());
                else ps.setNull(2, Types.INTEGER);

                ps.setDate(3, reserva.getFechaReserva());
                ps.setTime(4, reserva.getHora());
                ps.setInt(5, reserva.getNumPersonas());
                ps.setString(6, reserva.getEstado());
                ps.setString(7, reserva.getObservaciones());
                ps.setInt(8, reserva.getIdReserva());

                int affected = ps.executeUpdate();
                return affected > 0;
            }
        } catch (SQLException e) {
            System.err.println("[ReservaDAO.update] Error SQL:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM reserva WHERE id_reserva = ?";
        try (Connection conn = DBUtil.getConnection()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                int affected = ps.executeUpdate();
                return affected > 0;
            }
        } catch (SQLException e) {
            System.err.println("[ReservaDAO.delete] Error SQL:");
            e.printStackTrace();
            return false;
        }
    }

    // Helper: mapear ResultSet -> Reserva (campos principales)
    private Reserva mapRow(ResultSet rs) throws SQLException {
        Reserva r = new Reserva();
        r.setIdReserva(rs.getInt("id_reserva"));
        int idClient = rs.getInt("id_cliente");
        if (!rs.wasNull()) r.setIdCliente(idClient); else r.setIdCliente(null);
        int idMesa = rs.getInt("id_mesa");
        if (!rs.wasNull()) r.setIdMesa(idMesa); else r.setIdMesa(null);
        r.setFechaReserva(rs.getDate("fecha_reserva"));
        r.setHora(rs.getTime("hora"));
        r.setNumPersonas(rs.getInt("num_personas"));
        r.setEstado(rs.getString("estado"));
        r.setObservaciones(rs.getString("observaciones"));
        r.setCreatedAt(rs.getTimestamp("created_at"));
        return r;
    }
}