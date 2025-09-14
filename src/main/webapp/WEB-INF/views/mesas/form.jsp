<%--
  Created by IntelliJ IDEA.
  User: RNK
  Date: 14/09/2025
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="editing" value="${not empty mesa}" />

<html>
<head>
    <title>
        <c:choose>
            <c:when test="${editing}">Editar Mesa</c:when>
            <c:otherwise>Crear Mesa</c:otherwise>
        </c:choose>
    </title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/mesasform.css">
</head>
<body>
<div class="container">
    <div class="header">
        <h1>
            <c:choose>
                <c:when test="${editing}">Editar Mesa</c:when>
                <c:otherwise>Crear Mesa</c:otherwise>
            </c:choose>
        </h1>
        <p>Configuración de mesas</p>
    </div>

    <div class="form-content">
        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>

        <c:choose>
        <c:when test="${editing}">
        <form method="post" action="${pageContext.request.contextPath}/mesas/edit">
            <input type="hidden" name="id" value="${mesa.idMesa}" />
            </c:when>
            <c:otherwise>
            <form method="post" action="${pageContext.request.contextPath}/mesas/create">
                </c:otherwise>
                </c:choose>

                <div class="form-row">
                    <div class="form-group">
                        <label for="numero">Número de mesa</label>
                        <c:choose>
                            <c:when test="${editing}">
                                <input type="text" id="numero" name="numero" value="${mesa.numero}" required />
                            </c:when>
                            <c:otherwise>
                                <input type="text" id="numero" name="numero" value="${param.numero}" required />
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <div class="form-group">
                        <label for="capacidad">Capacidad</label>
                        <c:choose>
                            <c:when test="${editing}">
                                <input type="number" id="capacidad" name="capacidad" min="1" max="20" value="${mesa.capacidad}" required />
                            </c:when>
                            <c:otherwise>
                                <input type="number" id="capacidad" name="capacidad" min="1" max="20" value="${param.capacidad}" required />
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <div class="form-group full-width">
                    <label for="ubicacion">Ubicación</label>
                    <c:choose>
                        <c:when test="${editing}">
                            <input type="text" id="ubicacion" name="ubicacion" value="${mesa.ubicacion}" />
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="ubicacion" name="ubicacion" value="${param.ubicacion}" />
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="form-group full-width">
                    <label for="estado">Estado</label>
                    <select name="estado" id="estado">
                        <c:forEach var="estadoOpt" items="${['disponible','ocupada','reservada','fuera_de_servicio']}">
                            <option value="${estadoOpt}"
                                    <c:if test="${editing && mesa.estado==estadoOpt}">selected</c:if>>
                                    ${estadoOpt}
                            </option>
                        </c:forEach>
                    </select>
                    <div class="status-info">
                        El estado se actualiza automáticamente según las reservas y ocupación
                    </div>
                </div>

                <button type="submit" class="btn-submit">Guardar</button>
            </form>

            <a href="${pageContext.request.contextPath}/mesas" class="back-link">← Volver a la lista</a>
    </div>
</div>
</body>
</html>
