package com.example.s04evaluacion01.servlet;

import com.example.s04evaluacion01.dao.ReservaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/reservas/delete")
public class ReservaDeleteServlet extends HttpServlet {
    private final ReservaDAO reservaDAO = new ReservaDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr == null) {
            resp.sendRedirect(req.getContextPath() + "/reservas?msg=ID+no+proporcionado");
            return;
        }
        try {
            int id = Integer.parseInt(idStr);
            boolean deleted = reservaDAO.delete(id);
            resp.sendRedirect(req.getContextPath() + "/reservas?msg=" + (deleted ? "Reserva+eliminada" : "No+se+elimino"));
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/reservas?msg=ID+inválido");
        }
    }
}
