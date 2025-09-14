package com.example.s04evaluacion01.dao;

import com.example.s04evaluacion01.model.Cliente;
import com.example.s04evaluacion01.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public List<Cliente> getAll() {
        List<Cliente> list = new ArrayList<>();
        String sql = "SELECT id_cliente, nombre, telefono, email, dni, created_at FROM cliente ORDER BY nombre";
        try (Connection conn = DBUtil.getConnection()) {
            if (conn == null) return list;
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cliente c = new Cliente();
                    c.setIdCliente(rs.getInt("id_cliente"));
                    c.setNombre(rs.getString("nombre"));
                    c.setTelefono(rs.getString("telefono"));
                    c.setEmail(rs.getString("email"));
                    c.setDni(rs.getString("dni"));
                    list.add(c);
                }
            }
        } catch (SQLException e) {
            System.err.println("[ClienteDAO.getAll] Error SQL:");
            e.printStackTrace();
        }
        return list;
    }

    public Cliente getById(int id) {
        String sql = "SELECT id_cliente, nombre, telefono, email, dni, created_at FROM cliente WHERE id_cliente = ?";
        try (Connection conn = DBUtil.getConnection()) {
            if (conn == null) return null;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Cliente c = new Cliente();
                        c.setIdCliente(rs.getInt("id_cliente"));
                        c.setNombre(rs.getString("nombre"));
                        c.setTelefono(rs.getString("telefono"));
                        c.setEmail(rs.getString("email"));
                        c.setDni(rs.getString("dni"));
                        return c;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("[ClienteDAO.getById] Error SQL:");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Inserta cliente; devuelve id generado o -1 en error.
     */
    public int create(Cliente cliente) {
        String sql = "INSERT INTO cliente (nombre, telefono, email, dni) VALUES (?,?,?,?)";
        try (Connection conn = DBUtil.getConnection()) {
            if (conn == null) return -1;
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, cliente.getNombre());
                ps.setString(2, cliente.getTelefono());
                ps.setString(3, cliente.getEmail());
                ps.setString(4, cliente.getDni());
                int affected = ps.executeUpdate();
                if (affected == 0) {
                    System.err.println("[ClienteDAO.create] No se insertó el cliente.");
                    return -1;
                }
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        int id = keys.getInt(1);
                        cliente.setIdCliente(id);
                        return id;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("[ClienteDAO.create] Error SQL:");
            e.printStackTrace();
        }
        return -1;
    }

    public boolean update(Cliente cliente) {
        String sql = "UPDATE cliente SET nombre = ?, telefono = ?, email = ?, dni = ? WHERE id_cliente = ?";
        try (Connection conn = DBUtil.getConnection()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, cliente.getNombre());
                ps.setString(2, cliente.getTelefono());
                ps.setString(3, cliente.getEmail());
                ps.setString(4, cliente.getDni());
                ps.setInt(5, cliente.getIdCliente());
                int affected = ps.executeUpdate();
                return affected > 0;
            }
        } catch (SQLException e) {
            System.err.println("[ClienteDAO.update] Error SQL:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try (Connection conn = DBUtil.getConnection()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                int affected = ps.executeUpdate();
                return affected > 0;
            }
        } catch (SQLException e) {
            System.err.println("[ClienteDAO.delete] Error SQL:");
            e.printStackTrace();
            return false;
        }
    }
}
