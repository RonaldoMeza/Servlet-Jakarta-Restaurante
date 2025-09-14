
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Restaurante - Panel</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/index.css">
</head>
<body>
<div class="panel-container">
    <h1>🍽️ Restaurante</h1>
    <p class="subtitle">Panel de Administración</p>
    <ul>
        <li><a href="${pageContext.request.contextPath}/clientes" class="clientes-link">
            <span class="icon">👥</span>Clientes (CRUD)
        </a></li>
        <li><a href="${pageContext.request.contextPath}/mesas" class="mesas-link">
            <span class="icon">🪑</span>Mesas (CRUD)
        </a></li>
        <li><a href="${pageContext.request.contextPath}/reservas" class="reservas-link">
            <span class="icon">📅</span>Reservas (CRUD)
        </a></li>
    </ul>
</div>
</body>
</html>