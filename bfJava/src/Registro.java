import repository.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Registro {

    public static void registrar(String login, String senha) throws SQLException {
        String consultarLogins = "SELECT * FROM usuarios WHERE login = ?";

        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(consultarLogins)) {

            stmt.setString(1, login);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Infelizmente este usuário já existe.");
                } else {
                    String sql = "INSERT INTO usuarios (login, senha, cena_id) VALUES (?, ?, 1)";

                    try (PreparedStatement insertStmt = conn.prepareStatement(sql)) {
                        insertStmt.setString(1, login);
                        insertStmt.setString(2, senha);
                        int rowsAffected = insertStmt.executeUpdate();

                        if (rowsAffected > 0) {
                            System.out.println("Usuário registrado com sucesso!");
                        } else {
                            System.out.println("Erro ao registrar o usuário.");
                        }
                    } catch (SQLException e) {
                        System.out.println("Erro na realização do registro.");
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}