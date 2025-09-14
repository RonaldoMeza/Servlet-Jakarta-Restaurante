package com.example.s04evaluacion01.servlet;

import com.example.s04evaluacion01.dao.ClienteDAO;
import com.example.s04evaluacion01.dao.MesaDAO;
import com.example.s04evaluacion01.dao.ReservaDAO;
import com.example.s04evaluacion01.model.Cliente;
import com.example.s04evaluacion01.model.Mesa;
import com.example.s04evaluacion01.model.Reserva;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@WebServlet("/reservas/create")
public class ReservaCreateServlet extends HttpServlet {
    private final ReservaDAO reservaDAO = new ReservaDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final MesaDAO mesaDAO = new MesaDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // poblar selects
        List<Cliente> clientes = clienteDAO.getAll();
        List<Mesa> mesas = mesaDAO.getAll();
        req.setAttribute("clientes", clientes);
        req.setAttribute("mesas", mesas);
        req.getRequestDispatcher("/WEB-INF/views/reservas/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idClienteStr = req.getParameter("id_cliente");
            String idMesaStr = req.getParameter("id_mesa");
            String fechaStr = req.getParameter("fecha_reserva"); // yyyy-MM-dd
            String horaStr = req.getParameter("hora"); // HH:mm or HH:mm:ss
            String numPersonasStr = req.getParameter("num_personas");
            String estado = req.getParameter("estado");
            String observ = req.getParameter("observaciones");

            Integer idCliente = (idClienteStr == null || idClienteStr.isBlank()) ? null : Integer.valueOf(idClienteStr);
            Integer idMesa = (idMesaStr == null || idMesaStr.isBlank()) ? null : Integer.valueOf(idMesaStr);
            Date fecha = Date.valueOf(fechaStr);
            Time hora = Time.valueOf(horaStr.length()<=5 ? horaStr + ":00" : horaStr);
            int numP = Integer.parseInt(numPersonasStr);

            Reserva r = new Reserva(idCliente, idMesa, fecha, hora, numP, estado == null ? "pendiente" : estado, observ);
            int id = reservaDAO.create(r);
            if (id > 0) {
                resp.sendRedirect(req.getContextPath() + "/reservas?msg=Reserva+creada+id%3D" + id);
            } else {
                req.setAttribute("error", "No se pudo crear la reserva.");
                doGet(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", "Datos inválidos: " + e.getMessage());
            doGet(req, resp);
        }
    }
}
