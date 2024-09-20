import model.Item;
import repository.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameSave {

    public static void saveGame(Player player) {
        try (Connection conn = DB.conectar()) {
            // Atualizar a cena no banco de dados
            String updateSceneQuery = "UPDATE player_progress SET current_scene = ? WHERE player_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateSceneQuery)) {
                pstmt.setInt(1, player.getCurrentSceneId());
                pstmt.setInt(2, player.getId());
                pstmt.executeUpdate();
            }

            // Limpar inventário antigo
            String deleteInventoryQuery = "DELETE FROM Inventory WHERE Id_player = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteInventoryQuery)) {
                pstmt.setInt(1, player.getId());
                pstmt.executeUpdate();
            }

            // Salvar o novo inventário
            String insertInventoryQuery = "INSERT INTO Inventory (Id_player, Id_item) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertInventoryQuery)) {
                for (Item item : player.getInventory()) {
                    pstmt.setInt(1, player.getId());
                    pstmt.setInt(2, item.getId());
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void loadGame(Player player) {
        try (Connection conn = DB.conectar()) {
            // Carregar a cena atual
            String loadSceneQuery = "SELECT current_scene FROM player_progress WHERE player_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(loadSceneQuery)) {
                pstmt.setInt(1, player.getId());
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    player.setCurrentSceneId(rs.getInt("current_scene"));
                }
            }

            // Carregar o inventário
            String loadInventoryQuery = "SELECT i.Id_itens, i.Nome, i.Descricao, i.Carregavel " +
                    "FROM Inventory iv " +
                    "JOIN Items i ON iv.Id_item = i.Id_itens " +
                    "WHERE iv.Id_player = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(loadInventoryQuery)) {
                pstmt.setInt(1, player.getId());
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int itemId = rs.getInt("Id_itens");
                    String itemName = rs.getString("Nome");
                    String itemDescription = rs.getString("Descricao");
                    boolean itemCarregavel = rs.getBoolean("Carregavel");
                    Item item = new Item(itemId, itemName, itemDescription, itemCarregavel);
                    player.addItemToInventory(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void restartGame(Player player) {
        try (Connection conn = DB.conectar()) {
            // Definir a cena inicial
            String resetSceneQuery = "UPDATE player_progress SET current_scene = 1 WHERE player_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(resetSceneQuery)) {
                pstmt.setInt(1, player.getId());
                pstmt.executeUpdate();
            }

            // Limpar o inventário
            String clearInventoryQuery = "DELETE FROM Inventory WHERE Id_player = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(clearInventoryQuery)) {
                pstmt.setInt(1, player.getId());
                pstmt.executeUpdate();
            }

            // Reiniciar o inventário
            player.clearInventory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}