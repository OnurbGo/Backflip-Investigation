package repository;

import model.Cena;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CenaDAO {
    public Cena getCenaById(int cenaId) {
        String sql = "SELECT * FROM cenas WHERE Id_cenas = ?";
        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cenaId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cena(
                            rs.getInt("Id_cenas"),
                            rs.getString("titulo"),
                            rs.getString("descricao")
                    );
                } else {
                    System.out.println("Cena não encontrada.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
