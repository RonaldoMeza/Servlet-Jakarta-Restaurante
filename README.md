# 🍽️ ERP Restaurante - Gestión de Clientes, Mesas y Reservas

Proyecto académico desarrollado con **Java Servlets, JSP, JSTL, DAO y MySQL**, desplegado en **Apache Tomcat**.  
El objetivo fue implementar la gestión de 3 CRUDs principales en un restaurante: **Clientes, Mesas y Reservas**.

---

## 🚀 Tecnologías utilizadas

- **Java 17**
- **Servlets y JSP** (Jakarta EE)
- **JSTL** para la vista dinámica
- **DAO Pattern** para acceso a datos
- **MySQL** como base de datos relacional
- **Apache Tomcat 10.1.x** como servidor de aplicaciones
- **Maven** como gestor de dependencias
- **CSS** para el estilo de los formularios y tablas

---

## 📂 Estructura del proyecto

src/
├── main/
│ ├── java/
│ │ ├── com.example.dao/ # Clases DAO
│ │ ├── com.example.model/ # Clases modelo (Cliente, Mesa, Reserva)
│ │ ├── com.example.servlet/ # Servlets (Create, Edit, Delete, List)
│ │ └── com.example.utils/ # DBUtil (gestión de conexión)
│ └── webapp/
│ ├── clientes/ # JSPs de clientes
│ ├── mesas/ # JSPs de mesas
│ ├── reservas/ # JSPs de reservas
│ ├── static/css/ # Estilos CSS
│ └── index.jsp

---

## 🗄️ Base de datos

Base de datos: **restaurante**

    CREATE TABLE cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    telefono VARCHAR(50),
    email VARCHAR(255),
    dni VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
    
    CREATE TABLE mesa (
    id_mesa INT AUTO_INCREMENT PRIMARY KEY,
    numero INT NOT NULL UNIQUE,
    capacidad INT NOT NULL DEFAULT 2,
    ubicacion VARCHAR(255),
    estado VARCHAR(50) DEFAULT 'disponible'
    );
    
    CREATE TABLE reserva (
    id_reserva INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT,
    id_mesa INT,
    fecha_reserva DATE NOT NULL,
    hora TIME NOT NULL,
    num_personas INT DEFAULT 1,
    estado VARCHAR(50) DEFAULT 'pendiente',
    observaciones TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_reserva_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE SET NULL,
    CONSTRAINT fk_reserva_mesa FOREIGN KEY (id_mesa) REFERENCES mesa(id_mesa) ON DELETE SET NULL
    );

📌 Funcionalidades

✅ Gestión de Clientes

Crear, editar, listar y eliminar clientes.

Validación de campos básicos como nombre, email y DNI.

✅ Gestión de Mesas

Registrar mesas con número, capacidad y ubicación.

Editar y eliminar mesas existentes.

✅ Gestión de Reservas

Crear reservas seleccionando cliente y mesa.

Editar y eliminar reservas.

Validación de fecha y hora.

