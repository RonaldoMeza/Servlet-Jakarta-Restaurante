<%--
  Created by IntelliJ IDEA.
  User: RNK
  Date: 14/09/2025
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Clientes</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/clienteslist.css">
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Gestión de Clientes</h1>
        <div class="header-actions">
            <a href="${pageContext.request.contextPath}/clientes/create" class="btn btn-primary">
                + Crear cliente
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
            <c:when test="${empty clientes}">
                <div class="empty-state">
                    <h3>No hay clientes registrados</h3>
                    <p>Comienza agregando tu primer cliente</p>
                    <br>
                    <a href="${pageContext.request.contextPath}/clientes/create" class="btn btn-primary">
                        + Crear primer cliente
                    </a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="table-wrapper">
                    <table>
                        <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Teléfono</th>
                            <th>Email</th>
                            <th>DNI</th>
                            <th>Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="c" items="${clientes}">
                            <tr>

                                <td><strong>${c.nombre}</strong></td>
                                <td>${c.telefono}</td>
                                <td>${c.email}</td>
                                <td>${c.dni}</td>
                                <td>
                                    <div class="actions">
                                        <a href="${pageContext.request.contextPath}/clientes/edit?id=${c.idCliente}"
                                           class="btn btn-edit">Editar</a>
                                        <a href="${pageContext.request.contextPath}/clientes/delete?id=${c.idCliente}"
                                           class="btn btn-delete"
                                           onclick="return confirm('¿Estás seguro de eliminar este cliente?')">Eliminar</a>
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