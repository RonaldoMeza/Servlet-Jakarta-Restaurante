package com.example.s04evaluacion01.servlet;

import com.example.s04evaluacion01.dao.MesaDAO;
import com.example.s04evaluacion01.model.Mesa;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/mesas/edit")
public class MesaEditServlet extends HttpServlet {
    private final MesaDAO mesaDAO = new MesaDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr == null) {
            resp.sendRedirect(req.getContextPath() + "/mesas?msg=ID+no+proporcionado");
            return;
        }
        try {
            int id = Integer.parseInt(idStr);
            Mesa m = mesaDAO.getById(id);
            if (m == null) {
                resp.sendRedirect(req.getContextPath() + "/mesas?msg=Mesa+no+encontrada");
                return;
            }
            req.setAttribute("mesa", m);
            req.getRequestDispatcher("/WEB-INF/views/mesas/form.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/mesas?msg=ID+inválido");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // recibir parámetros
        String idStr = req.getParameter("id");
        String numeroStr = req.getParameter("numero");
        String capacidadStr = req.getParameter("capacidad");
        String ubicacion = req.getParameter("ubicacion");
        String estado = req.getParameter("estado");

        String error = null;
        int id=0, numero=0, capacidad=0;
        try { id = Integer.parseInt(idStr); } catch (Exception e) { error = "ID inválido."; }
        try { numero = Integer.parseInt(numeroStr); } catch (Exception e) { if (error==null) error = "Número inválido."; }
        try { capacidad = Integer.parseInt(capacidadStr); } catch (Exception e) { if (error==null) error = "Capacidad inválida."; }

        if (error != null) {
            req.setAttribute("error", error);
            // reponer datos mínimos para el form
            Mesa m = new Mesa();
            m.setIdMesa(id);
            m.setNumero(numero);
            m.setCapacidad(capacidad);
            m.setUbicacion(ubicacion);
            m.setEstado(estado);
            req.setAttribute("mesa", m);
            req.getRequestDispatcher("/WEB-INF/views/mesas/form.jsp").forward(req, resp);
            return;
        }

        Mesa m = new Mesa(id, numero, capacidad, ubicacion, estado);
        boolean ok = mesaDAO.update(m);
        if (ok) {
            resp.sendRedirect(req.getContextPath() + "/mesas?msg=Mesa+actualizada");
        } else {
            req.setAttribute("error", "No se pudo actualizar la mesa.");
            req.setAttribute("mesa", m);
            req.getRequestDispatcher("/WEB-INF/views/mesas/form.jsp").forward(req, resp);
        }
    }
}