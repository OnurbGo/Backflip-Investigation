package repository;

import model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAO {
    public Item getItemByName(String nome) {
        String sql = "SELECT * FROM items WHERE Nome = ?";
        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("Id_itens");
                    String descricao = rs.getString("Descricao");
                    boolean carregavel = rs.getBoolean("Carregavel");
                    int combinedItemId = rs.getInt("combined_item_id");

                    // Lógica para obter o item combinado, se aplicável
                    Item combinedItem = null;
                    if (combinedItemId > 0) {
                        combinedItem = getItemById(combinedItemId);
                    }

                    return new Item(id, nome, descricao, carregavel, combinedItem);
                } else {
                    System.out.println("Item com o nome '" + nome + "' não encontrado.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private Item getItemById(int id) {
        String sql = "SELECT * FROM items WHERE Id_itens = ?";
        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("Nome");
                    String descricao = rs.getString("Descricao");
                    boolean carregavel = rs.getBoolean("Carregavel");

                    return new Item(id, nome, descricao, carregavel, null);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
