<%--
  Created by IntelliJ IDEA.
  User: RNK
  Date: 14/09/2025
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="editing" value="${not empty cliente}" />

<html>
<head>
    <title>
        <c:choose>
            <c:when test="${editing}">Editar Cliente</c:when>
            <c:otherwise>Crear Cliente</c:otherwise>
        </c:choose>
    </title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/clientesform.css">
</head>
<body>
<div class="container">
    <div class="header">
        <h1>
            <c:choose>
                <c:when test="${editing}">Editar Cliente</c:when>
                <c:otherwise>Crear Cliente</c:otherwise>
            </c:choose>
        </h1>
        <p>Información del cliente</p>
    </div>

    <div class="form-content">
        <c:if test="${not empty error}">
            <div class="error-message">
                    ${error}
            </div>
        </c:if>

        <c:choose>
        <c:when test="${editing}">
        <form method="post" action="${pageContext.request.contextPath}/clientes/edit">
            <input type="hidden" name="id" value="${cliente.idCliente}" />
            </c:when>
            <c:otherwise>
            <form method="post" action="${pageContext.request.contextPath}/clientes/create">
                </c:otherwise>
                </c:choose>

                <div class="form-group">
                    <label for="nombre">Nombre completo</label>
                    <c:choose>
                        <c:when test="${editing}">
                            <input type="text" id="nombre" name="nombre" value="${cliente.nombre}" required />
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="nombre" name="nombre" value="${param.nombre}" required />
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="form-group">
                    <label for="telefono">Teléfono</label>
                    <c:choose>
                        <c:when test="${editing}">
                            <input type="tel" id="telefono" name="telefono" value="${cliente.telefono}" />
                        </c:when>
                        <c:otherwise>
                            <input type="tel" id="telefono" name="telefono" value="${param.telefono}" />
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="form-group">
                    <label for="email">Email</label>
                    <c:choose>
                        <c:when test="${editing}">
                            <input type="email" id="email" name="email" value="${cliente.email}" />
                        </c:when>
                        <c:otherwise>
                            <input type="email" id="email" name="email" value="${param.email}" />
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="form-group">
                    <label for="dni">DNI</label>
                    <c:choose>
                        <c:when test="${editing}">
                            <input type="text" id="dni" name="dni" value="${cliente.dni}" />
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="dni" name="dni" value="${param.dni}" />
                        </c:otherwise>
                    </c:choose>
                </div>

                <button type="submit" class="btn-submit">Guardar</button>
            </form>

            <a href="${pageContext.request.contextPath}/clientes" class="back-link">
                ← Volver a la lista
            </a>
    </div>
</div>
</body>
</html>

