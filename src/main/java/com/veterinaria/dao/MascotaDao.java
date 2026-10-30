package com.veterinaria.dao;

import com.veterinaria.beans.Mascota;
import com.veterinaria.beans.Especie;
import com.veterinaria.beans.Veterinario;
import com.veterinaria.beans.Dueno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MascotaDao extends DaoBase {

    // Guardamos estos datos para que crear() y borrar() no necesiten parametros.
    private Mascota mascota;
    private int idMascota;

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public ArrayList<Mascota> listarMascotas(String especieId, String veterinarioId)
            throws SQLException {
        ArrayList<Mascota> lista = new ArrayList<>();

        String sql = "SELECT m.idmascota, m.nombre, m.edad, m.peso, " +
                "e.idespecie, e.nombre AS especie, " +
                "v.idveterinario, v.nombre AS veterinario, " +
                "d.iddueno, d.nombre AS dueno " +
                "FROM mascota m " +
                "INNER JOIN especie e ON m.especie_id = e.idespecie " +
                "INNER JOIN veterinario v ON m.veterinario_id = v.idveterinario " +
                "INNER JOIN dueno d ON m.dueno_id = d.iddueno ";

        boolean hayEspecie = especieId != null && !especieId.equals("");
        boolean hayVeterinario = veterinarioId != null && !veterinarioId.equals("");
        if (hayEspecie) sql += "WHERE m.especie_id = ? ";
        if (hayVeterinario) sql += hayEspecie ? "AND m.veterinario_id = ? " : "WHERE m.veterinario_id = ? ";
        sql += "ORDER BY m.idmascota";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int n = 1;
            if (hayEspecie) pstmt.setInt(n++, Integer.parseInt(especieId));
            if (hayVeterinario) pstmt.setInt(n++, Integer.parseInt(veterinarioId));

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Mascota mascota = new Mascota();
                    mascota.setIdmascota(rs.getInt("idmascota"));
                    mascota.setNombre(rs.getString("nombre"));
                    mascota.setEdad(rs.getInt("edad"));
                    mascota.setPeso(rs.getDouble("peso"));

                    Especie especie = new Especie();
                    especie.setIdespecie(rs.getInt("idespecie"));
                    especie.setNombre(rs.getString("especie"));
                    mascota.setEspecie(especie);

                    Veterinario veterinario = new Veterinario();
                    veterinario.setIdveterinario(rs.getInt("idveterinario"));
                    veterinario.setNombre(rs.getString("veterinario"));
                    mascota.setVeterinario(veterinario);

                    Dueno dueno = new Dueno();
                    dueno.setIddueno(rs.getInt("iddueno"));
                    dueno.setNombre(rs.getString("dueno"));
                    mascota.setDueno(dueno);
                    lista.add(mascota);
                }
            }
        }
        return lista;
    }

    @Override
    public void crear() throws SQLException {
        if (mascota == null || mascota.getEspecie() == null ||
                mascota.getVeterinario() == null || mascota.getDueno() == null) {
            throw new SQLException("Faltan datos de la mascota.");
        }
        String sql = "INSERT INTO mascota (nombre, edad, peso, especie_id, veterinario_id, dueno_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mascota.getNombre());
            pstmt.setInt(2, mascota.getEdad());
            pstmt.setDouble(3, mascota.getPeso());
            pstmt.setInt(4, mascota.getEspecie().getIdespecie());
            pstmt.setInt(5, mascota.getVeterinario().getIdveterinario());
            pstmt.setInt(6, mascota.getDueno().getIddueno());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void borrar() throws SQLException {
        if (idMascota <= 0) throw new SQLException("ID de mascota no valido.");
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM mascota WHERE idmascota = ?")) {
            pstmt.setInt(1, idMascota);
            pstmt.executeUpdate();
        }
    }
}
