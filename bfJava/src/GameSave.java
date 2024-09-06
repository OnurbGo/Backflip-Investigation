import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameSave {

    public static void saveGame(Player player) {
        String sql = "UPDATE usuarios SET cena_id = ? WHERE login = ?";

        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, player.getCurrentSceneId());
            stmt.setString(2, player.getLogin());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Jogo salvo com sucesso!");
            } else {
                System.out.println("Erro ao salvar o jogo.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar o jogo.");
        }
    }

    public static void loadGame(Player player) {
        String sql = "SELECT cena_id FROM usuarios WHERE login = ?";

        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, player.getLogin());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int currentSceneId = rs.getInt("cena_id");
                    player.setCurrentSceneId(currentSceneId);
                } else {
                    System.out.println("Não foi possível carregar o progresso.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar o progresso.");
        }
    }
}
