package com.veterinaria.dao;

import com.veterinaria.beans.Veterinario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VeterinarioDao extends DaoBase {

    public ArrayList<Veterinario> listarVeterinarios() throws SQLException {
        ArrayList<Veterinario> lista = new ArrayList<>();

        String sql = "SELECT * FROM veterinario";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Veterinario dato = new Veterinario();
                dato.setIdveterinario(rs.getInt("idveterinario"));
                dato.setNombre(rs.getString("nombre"));
                dato.setEspecialidad(rs.getString("especialidad"));
                lista.add(dato);
            }
        }
        return lista;
    }

    // No se crean ni borran veterinarios en este laboratorio.
    @Override
    public void crear() throws SQLException {
        //throw new UnsupportedOperationException("Operacion no requerida para veterinario");
    }

    @Override
    public void borrar() throws SQLException {
        //throw new UnsupportedOperationException("Operacion no requerida para veterinario");
    }
}
