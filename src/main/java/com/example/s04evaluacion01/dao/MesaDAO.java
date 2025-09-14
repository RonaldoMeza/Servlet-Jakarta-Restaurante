package com.example.s04evaluacion01.dao;

import com.example.s04evaluacion01.model.Mesa;
import com.example.s04evaluacion01.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la tabla `mesa`
 * Métodos: getAll, getById, create, update, delete.
 * Usa DBUtil.getConnection() (que devuelve Connection o null).
 */
public class MesaDAO {

    public List<Mesa> getAll() {
        List<Mesa> list = new ArrayList<>();
        String sql = "SELECT id_mesa, numero, capacidad, ubicacion, estado FROM mesa ORDER BY numero";
        try (Connection conn = DBUtil.getConnection()) {
            if (conn == null) return list;
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Mesa m = mapRow(rs);
                    list.add(m);
                }
            }
        } catch (SQLException e) {
            System.err.println("[MesaDAO.getAll] Error SQL:");
            e.printStackTrace();
        }
        return list;
    }

    public Mesa getById(int id) {
        String sql = "SELECT id_mesa, numero, capacidad, ubicacion, estado FROM mesa WHERE id_mesa = ?";
        try (Connection conn = DBUtil.getConnection()) {
            if (conn == null) return null;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return mapRow(rs);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("[MesaDAO.getById] Error SQL:");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Inserta una nueva mesa. Si la inserción tiene éxito, retorna el id generado y lo setea en el objeto.
     * Retorna -1 si falla.
     */
    public int create(Mesa mesa) {
        String sql = "INSERT INTO mesa (numero, capacidad, ubicacion, estado) VALUES (?,?,?,?)";
        try (Connection conn = DBUtil.getConnection()) {
            if (conn == null) return -1;
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, mesa.getNumero());
                ps.setInt(2, mesa.getCapacidad());
                ps.setString(3, mesa.getUbicacion());
                ps.setString(4, mesa.getEstado());
                int affected = ps.executeUpdate();
                if (affected == 0) {
                    System.err.println("[MesaDAO.create] No se insertó la mesa.");
                    return -1;
                }
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        int id = keys.getInt(1);
                        mesa.setIdMesa(id);
                        return id;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("[MesaDAO.create] Error SQL:");
            e.printStackTrace();
        }
        return -1;
    }

    public boolean update(Mesa mesa) {
        String sql = "UPDATE mesa SET numero = ?, capacidad = ?, ubicacion = ?, estado = ? WHERE id_mesa = ?";
        try (Connection conn = DBUtil.getConnection()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, mesa.getNumero());
                ps.setInt(2, mesa.getCapacidad());
                ps.setString(3, mesa.getUbicacion());
                ps.setString(4, mesa.getEstado());
                ps.setInt(5, mesa.getIdMesa());
                int affected = ps.executeUpdate();
                return affected > 0;
            }
        } catch (SQLException e) {
            System.err.println("[MesaDAO.update] Error SQL:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM mesa WHERE id_mesa = ?";
        try (Connection conn = DBUtil.getConnection()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                int affected = ps.executeUpdate();
                return affected > 0;
            }
        } catch (SQLException e) {
            System.err.println("[MesaDAO.delete] Error SQL:");
            e.printStackTrace();
            return false;
        }
    }

    // Helper para mapear ResultSet a Mesa
    private Mesa mapRow(ResultSet rs) throws SQLException {
        Mesa m = new Mesa();
        m.setIdMesa(rs.getInt("id_mesa"));
        m.setNumero(rs.getInt("numero"));
        m.setCapacidad(rs.getInt("capacidad"));
        m.setUbicacion(rs.getString("ubicacion"));
        m.setEstado(rs.getString("estado"));
        return m;
    }
}
