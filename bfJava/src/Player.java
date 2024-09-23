import model.Item;
import repository.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;
    private String login;
    private int currentSceneId;

    public Player(int id, String login, int currentSceneId) {
        this.id = id;
        this.login = login;
        this.currentSceneId = currentSceneId;
    }

    // Adiciona um item ao inventário
    public void addItemToInventory(Item item) {
        if (isItemInInventory(item)) {
            System.out.println("O item " + item.getNome() + " já está no inventário.");
            return;
        }

        try (Connection conn = repository.DB.conectar()) {
            String insertItemSql = "INSERT INTO inventory (Id_player, Id_itens) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertItemSql)) {
                stmt.setInt(1, this.id);
                stmt.setInt(2, item.getId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar item ao inventário: " + e.getMessage());
        }
    }

    // Limpa o inventário
    public void clearInventory() {
        try (Connection conn = repository.DB.conectar()) {
            String deleteInventorySql = "DELETE FROM inventory WHERE Id_player = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteInventorySql)) {
                stmt.setInt(1, this.id); // Certifique-se de que this.id é o Id_player correto
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obtém o inventário do jogador
    public List<Item> getInventory() {
        List<Item> items = new ArrayList<>();
        try (Connection conn = repository.DB.conectar()) {
            String selectItemsSql = "SELECT i.Id_itens, i.Nome, i.Descricao FROM inventory inv JOIN items i ON inv.Id_itens = i.Id_itens WHERE inv.Id_player = ?";
            try (PreparedStatement stmt = conn.prepareStatement(selectItemsSql)) {
                stmt.setInt(1, this.id);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int itemId = rs.getInt("Id_itens");
                        String itemName = rs.getString("Nome");
                        String itemDescription = rs.getString("Descricao");
                        items.add(new Item(itemId, itemName, itemDescription, false));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    // Remove um item do inventário
    public void removeItemFromInventory(Item item) {
        try (Connection conn = repository.DB.conectar()) {
            String deleteItemSql = "DELETE FROM inventory WHERE Id_player = ? AND Id_itens = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteItemSql)) {
                stmt.setInt(1, this.id);
                stmt.setInt(2, item.getId());
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Item " + item.getNome() + " removido do inventário.");
                } else {
                    System.out.println("Item " + item.getNome() + " não encontrado no inventário.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isItemInInventory(Item item) {
        try (Connection conn = repository.DB.conectar()) {
            String checkItemSql = "SELECT * FROM inventory WHERE Id_player = ? AND Id_itens = ?";
            try (PreparedStatement stmt = conn.prepareStatement(checkItemSql)) {
                stmt.setInt(1, this.id);
                stmt.setInt(2, item.getId());
                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next();  // Retorna true se o item já estiver no inventário
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar se o item já está no inventário: " + e.getMessage());
        }
        return false;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getCurrentSceneId() {
        return currentSceneId;
    }

    public void setCurrentSceneId(int currentSceneId) {
        this.currentSceneId = currentSceneId;
    }
}
