import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    public static Player realizarLogin(String login, String senha) {
        String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?"; // Corrigido para 'usuarios'

        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Login bem-sucedido!");
                    int currentSceneId = rs.getInt("cena_id");
                    return new Player(login, currentSceneId);
                } else {
                    System.out.println("Login ou senha incorretos.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro na realização do login.");
        }
        return null; // Retorna null se o login falhar
    }
}
