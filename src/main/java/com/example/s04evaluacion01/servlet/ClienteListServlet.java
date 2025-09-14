package com.example.s04evaluacion01.servlet;

import com.example.s04evaluacion01.dao.ClienteDAO;
import com.example.s04evaluacion01.model.Cliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/clientes")
public class ClienteListServlet extends HttpServlet {
    private final ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cliente> lista = clienteDAO.getAll();
        req.setAttribute("clientes", lista);
        String msg = req.getParameter("msg");
        if (msg != null) req.setAttribute("msg", msg);
        req.getRequestDispatcher("/WEB-INF/views/clientes/list.jsp").forward(req, resp);
    }
}
