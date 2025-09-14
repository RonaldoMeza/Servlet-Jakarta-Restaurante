package com.example.s04evaluacion01.servlet;

import com.example.s04evaluacion01.dao.MesaDAO;
import com.example.s04evaluacion01.model.Mesa;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.List;


@WebServlet("/mesas")
public class MesaListServlet extends HttpServlet {
    private final MesaDAO mesaDAO = new MesaDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServerException, IOException, ServletException {
        List<Mesa> mesas = mesaDAO.getAll();
        req.setAttribute("mesas", mesas);

        // opcional: tomar mensaje de operación (msg param) y pasarlo a la vista
        String msg = req.getParameter("msg");
        if (msg != null && !msg.isBlank()) req.setAttribute("msg", msg);

        req.getRequestDispatcher("/WEB-INF/views/mesas/list.jsp").forward(req, resp);
    }
}