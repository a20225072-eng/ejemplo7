package com.veterinaria.dao;

import com.veterinaria.beans.Dueno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DuenoDao extends DaoBase {

    public ArrayList<Dueno> listarDuenos() throws SQLException {
        ArrayList<Dueno> lista = new ArrayList<>();

        String sql = "SELECT * FROM dueno";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Dueno dato = new Dueno();
                dato.setIddueno(rs.getInt("iddueno"));
                dato.setNombre(rs.getString("nombre"));
                dato.setTelefono(rs.getString("telefono"));
                lista.add(dato);
            }
        }
        return lista;
    }

    // No se crean ni borran duenos en este laboratorio.
    @Override
    public void crear() throws SQLException {
        //throw new UnsupportedOperationException("Operacion no requerida para dueno");
    }

    @Override
    public void borrar() throws SQLException {
       // throw new UnsupportedOperationException("Operacion no requerida para dueno");
    }
}
