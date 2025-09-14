<%--
  Created by IntelliJ IDEA.
  User: RNK
  Date: 14/09/2025
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Reservas</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/reservaslist.css">
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Gestión de Reservas</h1>
        <div class="header-actions">
            <a href="${pageContext.request.contextPath}/reservas/create" class="btn btn-primary">
                + Crear reserva
            </a>
            <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">
                ← Regresar
            </a>
        </div>
    </div>

    <c:if test="${not empty msg}">
        <div class="success-message">
            ✓ ${msg}
        </div>
    </c:if>

    <div class="table-container">
        <c:choose>
            <c:when test="${empty reservas}">
                <div class="empty-state">
                    <h3>No hay reservas registradas</h3>
                    <p>Comienza creando tu primera reserva</p>
                    <br>
                    <a href="${pageContext.request.contextPath}/reservas/create" class="btn btn-primary">
                        + Crear primera reserva
                    </a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="table-wrapper">
                    <table>
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Cliente</th>
                            <th>Mesa</th>
                            <th>Fecha</th>
                            <th>Hora</th>
                            <th>Personas</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="r" items="${reservas}">
                            <tr>
                                <td><strong>#${r.idReserva}</strong></td>
                                <td class="client-info">${r.clienteNombre}</td>
                                <td><strong>Mesa ${r.mesaNumero}</strong></td>
                                <td class="date-time">${r.fechaReserva}</td>
                                <td class="date-time">${r.hora}</td>
                                <td><strong>${r.numPersonas}</strong> personas</td>
                                <td>
                                            <span class="status-badge status-${r.estado}">
                                                <c:choose>
                                                    <c:when test="${r.estado == 'pendiente'}">Pendiente</c:when>
                                                    <c:when test="${r.estado == 'confirmada'}">Confirmada</c:when>
                                                    <c:when test="${r.estado == 'cancelada'}">Cancelada</c:when>
                                                    <c:when test="${r.estado == 'asistio'}">Asistió</c:when>
                                                    <c:otherwise>${r.estado}</c:otherwise>
                                                </c:choose>
                                            </span>
                                </td>
                                <td>
                                    <div class="actions">
                                        <a href="${pageContext.request.contextPath}/reservas/edit?id=${r.idReserva}"
                                           class="btn btn-edit">Editar</a>
                                        <a href="${pageContext.request.contextPath}/reservas/delete?id=${r.idReserva}"
                                           class="btn btn-delete"
                                           onclick="return confirm('¿Estás seguro de eliminar esta reserva?')">Eliminar</a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
