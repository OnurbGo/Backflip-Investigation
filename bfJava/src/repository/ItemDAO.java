package repository;

import model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAO {

    public Item getItemByName(String nome) {
        Item item = null;
        String sql = "SELECT Id_itens, Nome, Descricao, Carregavel, combined_item_id, id_cenas FROM items WHERE Nome = ?";

        try (Connection conn = repository.DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("Id_itens");
                    String descricao = rs.getString("Descricao");
                    boolean carregavel = rs.getBoolean("Carregavel");
                    int combinedItemId = rs.getInt("combined_item_id");
                    int idCena = rs.getInt("id_cenas");

                    // Lógica para obter o item combinado, se aplicável
                    Item combinedItem = null;
                    if (combinedItemId > 0) {
                        combinedItem = getItemById(combinedItemId);
                    }

                    item = new Item(id, nome, descricao, carregavel, combinedItem, idCena);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
        return item;
    }

    public Item getItemById(int id) { // Alterado para int
        Item item = null;
        String sql = "SELECT * FROM items WHERE Id_itens = ?";

        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id); // Usando int para o ID
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("Nome");
                    String descricao = rs.getString("Descricao");
                    boolean carregavel = rs.getBoolean("Carregavel");
                    item = new Item(id, nome, descricao, carregavel); // Usando o ID corretamente
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
}