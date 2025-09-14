package com.example.s04evaluacion01.servlet;

import com.example.s04evaluacion01.dao.ClienteDAO;
import com.example.s04evaluacion01.model.Cliente;
import com.example.s04evaluacion01.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/clientes/edit")
public class ClienteEditServlet extends HttpServlet {
    private final ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idStr = req.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/clientes?msg=ID+no+proporcionado");
            return;
        }
        try {
            int id = Integer.parseInt(idStr);
            Cliente c = clienteDAO.getById(id);
            if (c == null) {
                resp.sendRedirect(req.getContextPath() + "/clientes?msg=Cliente+no+encontrado");
                return;
            }
            // ⚠ Aquí usamos forward y seteamos el cliente
            req.setAttribute("cliente", c);
            req.getRequestDispatcher("/WEB-INF/views/clientes/form.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/clientes?msg=ID+inválido");
        }
        System.out.println("[ClienteEditServlet] doGet llamado con id = " + idStr);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String nombre = safe(req.getParameter("nombre"));
        String telefono = safe(req.getParameter("telefono"));
        String email = safe(req.getParameter("email"));
        String dni = safe(req.getParameter("dni"));

        String error = null;
        int id = 0;
        try {
            id = Integer.parseInt(idStr);
        } catch (Exception e) { error = "ID inválido."; }

        if (error == null && nombre.isEmpty()) error = "El nombre es obligatorio.";

        if (error != null) {
            req.setAttribute("error", error);
            Cliente c = new Cliente();
            c.setIdCliente(id);
            c.setNombre(nombre);
            c.setTelefono(telefono);
            c.setEmail(email);
            c.setDni(dni);
            req.setAttribute("cliente", c);
            req.getRequestDispatcher("/WEB-INF/views/clientes/form.jsp").forward(req, resp);
            return;
        }

        Cliente c = new Cliente(id, nombre, telefono, email, dni);
        boolean ok = clienteDAO.update(c);
        if (ok) {
            resp.sendRedirect(req.getContextPath() + "/clientes?msg=Cliente+actualizado");
        } else {
            req.setAttribute("error", "No se pudo actualizar el cliente.");
            req.setAttribute("cliente", c);
            req.getRequestDispatcher("/WEB-INF/views/clientes/form.jsp").forward(req, resp);
        }
    }

    private String safe(String s) {
        return s == null ? "" : s.trim();
    }
}
