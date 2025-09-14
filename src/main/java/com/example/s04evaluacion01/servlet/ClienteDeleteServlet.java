package com.example.s04evaluacion01.servlet;

import com.example.s04evaluacion01.dao.ClienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/clientes/delete")
public class ClienteDeleteServlet extends HttpServlet {
    private final ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr == null) {
            resp.sendRedirect(req.getContextPath() + "/clientes?msg=ID+no+proporcionado");
            return;
        }
        try {
            int id = Integer.parseInt(idStr);
            boolean deleted = clienteDAO.delete(id);
            resp.sendRedirect(req.getContextPath() + "/clientes?msg=" + (deleted ? "Cliente+eliminado" : "No+se+elimino"));
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/clientes?msg=ID+inválido");
        }
    }
}