import model.Item;
import repository.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameSave {

    public static void saveGame(Player player) {
        try (Connection conn = repository.DB.conectar()) {
            String updateSceneQuery = "UPDATE usuarios SET cena_id = ? WHERE Id_player = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateSceneQuery)) {
                pstmt.setInt(1, player.getCurrentSceneId());
                pstmt.setInt(2, player.getId());
                pstmt.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}