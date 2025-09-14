<%--
  Created by IntelliJ IDEA.
  User: RNK
  Date: 14/09/2025
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Mesas</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/mesaslist.css">
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Gestión de Mesas</h1>
        <div class="header-actions">
            <a href="${pageContext.request.contextPath}/mesas/create" class="btn btn-primary">
                + Crear mesa
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
            <c:when test="${empty mesas}">
                <div class="empty-state">
                    <h3>No hay mesas registradas</h3>
                    <p>Comienza agregando tu primera mesa</p>
                    <br>
                    <a href="${pageContext.request.contextPath}/mesas/create" class="btn btn-primary">
                        + Crear primera mesa
                    </a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="table-wrapper">
                    <table>
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Número</th>
                            <th>Capacidad</th>
                            <th>Ubicación</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="m" items="${mesas}">
                            <tr>
                                <td><strong>#${m.idMesa}</strong></td>
                                <td class="mesa-number">Mesa ${m.numero}</td>
                                <td class="capacity">${m.capacidad} personas</td>
                                <td class="location">
                                    <c:choose>
                                        <c:when test="${empty m.ubicacion}">
                                            <span style="color: #adb5bd;">Sin ubicación</span>
                                        </c:when>
                                        <c:otherwise>
                                            ${m.ubicacion}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                            <span class="status-badge status-${m.estado}">
                                                <c:choose>
                                                    <c:when test="${m.estado == 'disponible'}">Disponible</c:when>
                                                    <c:when test="${m.estado == 'ocupada'}">Ocupada</c:when>
                                                    <c:when test="${m.estado == 'reservada'}">Reservada</c:when>
                                                    <c:when test="${m.estado == 'fuera_de_servicio'}">Fuera de servicio</c:when>
                                                    <c:otherwise>${m.estado}</c:otherwise>
                                                </c:choose>
                                            </span>
                                </td>
                                <td>
                                    <div class="actions">
                                        <a href="${pageContext.request.contextPath}/mesas/edit?id=${m.idMesa}"
                                           class="btn btn-edit">Editar</a>
                                        <a href="${pageContext.request.contextPath}/mesas/delete?id=${m.idMesa}"
                                           class="btn btn-delete"
                                           onclick="return confirm('¿Estás seguro de eliminar esta mesa?')">Eliminar</a>
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
