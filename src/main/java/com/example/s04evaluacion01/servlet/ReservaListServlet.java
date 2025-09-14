package com.example.s04evaluacion01.servlet;

import com.example.s04evaluacion01.dao.ReservaDAO;
import com.example.s04evaluacion01.model.Reserva;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/reservas")
public class ReservaListServlet extends HttpServlet {
    private final ReservaDAO reservaDAO = new ReservaDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Reserva> lista = reservaDAO.getAll();
        req.setAttribute("reservas", lista);
        String msg = req.getParameter("msg");
        if (msg != null) req.setAttribute("msg", msg);
        req.getRequestDispatcher("/WEB-INF/views/reservas/list.jsp").forward(req, resp);
    }
}