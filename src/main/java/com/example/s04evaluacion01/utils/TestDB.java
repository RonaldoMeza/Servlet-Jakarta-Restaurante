package com.example.s04evaluacion01.utils;

import java.sql.Connection;

public class TestDB {
    public static void main(String[] args) {
        Connection conn = DBUtil.getConnection();
        if (conn != null) {
            System.out.println("[TestDB] Conexión abierta y lista para usarse.");
        } else {
            System.out.println("[TestDB] No se pudo abrir la conexión.");
        }
    }
}
