package com.veterinaria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DaoBase {

    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new SQLException("No se encontro el driver MySQL", ex);
        }

        String user = "root";
        String pass = "TU_PASSWORD"; // Cambia esto solo en tu computadora
        String url = "jdbc:mysql://localhost:3306/Veterinaria";

        return DriverManager.getConnection(url, user, pass);
    }

    // Metodos abstractos requeridos por el LAB7 (sin cuerpo ni parametros).
    public abstract void crear() throws SQLException;
    public abstract void borrar() throws SQLException;
}
