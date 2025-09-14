package com.example.s04evaluacion01.servlet;

import com.example.s04evaluacion01.dao.ClienteDAO;
import com.example.s04evaluacion01.model.Cliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/clientes/create")
public class ClienteCreateServlet extends HttpServlet {
    private final ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/clientes/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = safe(req.getParameter("nombre"));
        String telefono = safe(req.getParameter("telefono"));
        String email = safe(req.getParameter("email"));
        String dni = safe(req.getParameter("dni"));

        // validación mínima
        if (nombre.isEmpty()) {
            req.setAttribute("error", "El nombre es obligatorio.");
            req.setAttribute("nombre", nombre); req.setAttribute("telefono", telefono);
            req.setAttribute("email", email); req.setAttribute("dni", dni);
            req.getRequestDispatcher("/WEB-INF/views/clientes/form.jsp").forward(req, resp);
            return;
        }

        Cliente c = new Cliente(nombre, telefono, email, dni);
        int id = clienteDAO.create(c);
        if (id > 0) {
            resp.sendRedirect(req.getContextPath() + "/clientes?msg=Cliente+creado+id%3D" + id);
        } else {
            req.setAttribute("error", "No se pudo crear el cliente.");
            req.getRequestDispatcher("/WEB-INF/views/clientes/form.jsp").forward(req, resp);
        }
    }

    private String safe(String s) {
        return s == null ? "" : s.trim();
    }
}