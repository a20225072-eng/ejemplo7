package com.veterinaria.dao;

import com.veterinaria.beans.Especie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EspecieDao extends DaoBase {

    public ArrayList<Especie> listarEspecies() throws SQLException {
        ArrayList<Especie> lista = new ArrayList<>();

        String sql = "SELECT * FROM especie";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Especie dato = new Especie();
                dato.setIdespecie(rs.getInt("idespecie"));
                dato.setNombre(rs.getString("nombre"));
                lista.add(dato);
            }
        }
        return lista;
    }

    // No se crean ni borran especies en este laboratorio.
    @Override
    public void crear() throws SQLException {
        //throw new UnsupportedOperationException("Operacion no requerida para especie");
    }

    @Override
    public void borrar() throws SQLException {
        //throw new UnsupportedOperationException("Operacion no requerida para especie");
    }
}
