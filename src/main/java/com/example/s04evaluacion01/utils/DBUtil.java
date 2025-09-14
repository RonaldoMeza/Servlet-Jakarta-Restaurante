package com.example.s04evaluacion01.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/restaurante?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASS = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // importante para Tomcat
        } catch (ClassNotFoundException e) {
            System.out.println("[DBUtil] Error cargando driver: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("[DBUtil] ✅ Conexión establecida correctamente.");
            return conn;
        } catch (SQLException e) {
            System.out.println("[DBUtil] ❌ Error al conectar con la base de datos: " + e.getMessage());
            return null;
        }
    }
}