package com.example.s04evaluacion01.servlet;

import com.example.s04evaluacion01.dao.MesaDAO;
import com.example.s04evaluacion01.model.Mesa;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/mesas/create")
public class MesaCreateServlet extends HttpServlet {
    private final MesaDAO mesaDAO = new MesaDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // reenviar al formulario (sin mesa => modo crear)
        req.getRequestDispatcher("/WEB-INF/views/mesas/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // lectura básica y validación mínima
        String numeroStr = req.getParameter("numero");
        String capacidadStr = req.getParameter("capacidad");
        String ubicacion = req.getParameter("ubicacion");
        String estado = req.getParameter("estado");

        String error = null;
        int numero = 0;
        int capacidad = 0;
        try {
            numero = Integer.parseInt(numeroStr);
        } catch (Exception e) { error = "Número de mesa inválido."; }
        try {
            capacidad = Integer.parseInt(capacidadStr);
        } catch (Exception e) { if (error==null) error = "Capacidad inválida."; }

        if (error != null) {
            req.setAttribute("error", error);
            // mantener valores ingresados
            req.setAttribute("numero", numeroStr);
            req.setAttribute("capacidad", capacidadStr);
            req.setAttribute("ubicacion", ubicacion);
            req.setAttribute("estado", estado);
            req.getRequestDispatcher("/WEB-INF/views/mesas/form.jsp").forward(req, resp);
            return;
        }

        Mesa m = new Mesa(numero, capacidad, ubicacion, estado == null ? "disponible" : estado);
        int id = mesaDAO.create(m);
        if (id > 0) {
            // redirigir a lista con mensaje simple
            resp.sendRedirect(req.getContextPath() + "/mesas?msg=Mesa+creada+id%3D" + id);
        } else {
            req.setAttribute("error", "No se pudo crear la mesa. Revisa la consola del servidor.");
            req.getRequestDispatcher("/WEB-INF/views/mesas/form.jsp").forward(req, resp);
        }
    }
}
