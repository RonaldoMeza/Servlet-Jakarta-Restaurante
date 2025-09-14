package com.example.s04evaluacion01.servlet;

import com.example.s04evaluacion01.dao.MesaDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/mesas/delete")
public class MesaDeleteServlet extends HttpServlet {
    private final MesaDAO mesaDAO = new MesaDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws SecurityException, IOException {
        String idStr = req.getParameter("id");
        if (idStr == null) {
            resp.sendRedirect(req.getContextPath() + "/mesas?msg=ID+no+proporcionado");
            return;
        }
        try {
            int id = Integer.parseInt(idStr);
            boolean deleted = mesaDAO.delete(id);
            if (deleted) {
                resp.sendRedirect(req.getContextPath() + "/mesas?msg=Mesa+eliminada");
            } else {
                resp.sendRedirect(req.getContextPath() + "/mesas?msg=No+se+elimino+la+mesa");
            }
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/mesas?msg=ID+inválido");
        }
    }
}