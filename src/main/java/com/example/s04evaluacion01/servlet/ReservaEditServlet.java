package com.example.s04evaluacion01.servlet;

import com.example.s04evaluacion01.dao.ClienteDAO;
import com.example.s04evaluacion01.dao.MesaDAO;
import com.example.s04evaluacion01.dao.ReservaDAO;
import com.example.s04evaluacion01.model.Reserva;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

@WebServlet("/reservas/edit")
public class ReservaEditServlet extends HttpServlet {
    private final ReservaDAO reservaDAO = new ReservaDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final MesaDAO mesaDAO = new MesaDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr == null) {
            resp.sendRedirect(req.getContextPath() + "/reservas?msg=ID+no+proporcionado");
            return;
        }
        try {
            int id = Integer.parseInt(idStr);
            Reserva r = reservaDAO.getById(id);
            if (r == null) {
                resp.sendRedirect(req.getContextPath() + "/reservas?msg=Reserva+no+encontrada");
                return;
            }
            req.setAttribute("reserva", r);
            req.setAttribute("clientes", clienteDAO.getAll());
            req.setAttribute("mesas", mesaDAO.getAll());
            req.getRequestDispatcher("/WEB-INF/views/reservas/form.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/reservas?msg=ID+inválido");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String idClienteStr = req.getParameter("id_cliente");
            String idMesaStr = req.getParameter("id_mesa");
            String fechaStr = req.getParameter("fecha_reserva");
            String horaStr = req.getParameter("hora");
            int numP = Integer.parseInt(req.getParameter("num_personas"));
            String estado = req.getParameter("estado");
            String observ = req.getParameter("observaciones");

            Integer idCliente = (idClienteStr == null || idClienteStr.isBlank()) ? null : Integer.valueOf(idClienteStr);
            Integer idMesa = (idMesaStr == null || idMesaStr.isBlank()) ? null : Integer.valueOf(idMesaStr);
            Date fecha = Date.valueOf(fechaStr);
            Time hora = Time.valueOf(horaStr.length()<=5 ? horaStr + ":00" : horaStr);

            Reserva r = new Reserva(id, idCliente, idMesa, fecha, hora, numP, estado, observ, null);
            boolean ok = reservaDAO.update(r);
            if (ok) {
                resp.sendRedirect(req.getContextPath() + "/reservas?msg=Reserva+actualizada");
            } else {
                req.setAttribute("error", "No se pudo actualizar la reserva.");
                doGet(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", "Datos inválidos: " + e.getMessage());
            doGet(req, resp);
        }
    }
}