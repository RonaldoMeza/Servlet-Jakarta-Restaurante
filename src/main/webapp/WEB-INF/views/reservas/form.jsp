<%--
  Created by IntelliJ IDEA.
  User: RNK
  Date: 14/09/2025
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="editing" value="${not empty reserva}" />

<html>
<head>
    <title>
        <c:choose>
            <c:when test="${editing}">Editar Reserva</c:when>
            <c:otherwise>Crear Reserva</c:otherwise>
        </c:choose>
    </title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/reservasform.css">
</head>
<body>
<div class="container">
    <div class="header">
        <h1>
            <c:choose>
                <c:when test="${editing}">Editar Reserva</c:when>
                <c:otherwise>Crear Reserva</c:otherwise>
            </c:choose>
        </h1>
        <p>Gestión de reservas</p>
    </div>

    <div class="form-content">
        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>

        <c:choose>
        <c:when test="${editing}">
        <form method="post" action="${pageContext.request.contextPath}/reservas/edit">
            <input type="hidden" name="id" value="${reserva.idReserva}" />
            </c:when>
            <c:otherwise>
            <form method="post" action="${pageContext.request.contextPath}/reservas/create">
                </c:otherwise>
                </c:choose>

                <div class="form-row">
                    <div class="form-group">
                        <label for="id_cliente">Cliente</label>
                        <select name="id_cliente" id="id_cliente">
                            <option value="">-- Sin cliente --</option>
                            <c:forEach var="c" items="${clientes}">
                                <option value="${c.idCliente}"
                                        <c:if test="${editing && reserva.idCliente==c.idCliente}">selected</c:if>>
                                        ${c.nombre}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="id_mesa">Mesa</label>
                        <select name="id_mesa" id="id_mesa">
                            <option value="">-- Sin mesa --</option>
                            <c:forEach var="m" items="${mesas}">
                                <option value="${m.idMesa}"
                                        <c:if test="${editing && reserva.idMesa==m.idMesa}">selected</c:if>>
                                        ${m.numero} (capacidad ${m.capacidad})
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="fecha_reserva">Fecha</label>
                        <input type="date" id="fecha_reserva" name="fecha_reserva"
                               value="<c:out value='${editing ? reserva.fechaReserva : ""}'/>" required />
                    </div>
                    <div class="form-group">
                        <label for="hora">Hora</label>
                        <input type="time" id="hora" name="hora"
                               value="<c:out value='${editing ? reserva.hora : ""}'/>" required />
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="num_personas">Número de personas</label>
                        <input type="number" id="num_personas" name="num_personas" min="1"
                               value="<c:out value='${editing ? reserva.numPersonas : 1}'/>" />
                    </div>

                    <div class="form-group">
                        <label for="estado">Estado</label>
                        <select name="estado" id="estado">
                            <c:forEach var="estadoOpt" items="${['pendiente','confirmada','cancelada','asistio']}">
                                <option value="${estadoOpt}"
                                        <c:if test="${editing && reserva.estado==estadoOpt}">selected</c:if>>
                                        ${estadoOpt}
                                </option>
                            </c:forEach>
                        </select>
                        <div class="status-info">
                            Estados: Pendiente → Confirmada → Asistió / Cancelada
                        </div>
                    </div>
                </div>

                <div class="form-group full-width">
                    <label for="observaciones">Observaciones</label>
                    <c:choose>
                        <c:when test="${editing}">
                            <textarea id="observaciones" name="observaciones" rows="3">${reserva.observaciones}</textarea>
                        </c:when>
                        <c:otherwise>
                            <textarea id="observaciones" name="observaciones" rows="3">${param.observaciones}</textarea>
                        </c:otherwise>
                    </c:choose>
                </div>

                <button type="submit" class="btn-submit">Guardar</button>
            </form>

            <a href="${pageContext.request.contextPath}/reservas" class="back-link">← Volver a la lista</a>
    </div>
</div>
</body>
</html>

