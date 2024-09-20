import model.Cena;
import model.Item;
import repository.CenaDAO;
import repository.DB;
import repository.ItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandExecutor {
    private Player player;
    private ItemDAO itemDAO = new ItemDAO();
    private GameSave gameSave;
    CenaDAO cenaDAO = new CenaDAO();

    public CommandExecutor(Player player, GameSave gameSave) {
        this.player = player;
        this.gameSave = gameSave;
    }
        public void executeCommand(String comando1, String comando2, String item2, String arguments) {
        try (Connection conn = repository.DB.conectar()) {
            List<Item> inventory = null;

            System.out.println("Comando: " + comando1 + ", Argumentos: " + arguments);
            switch (comando1) {
                case "USE":
                    //usar item
                    System.out.println("Usando item: " + arguments);
                    break;
                case "GET":
                    System.out.println("Procurando item: " + arguments.trim());
                    Item item = itemDAO.getItemByName(arguments.trim());

                    if (item != null) {
                        if (item.isCarregavel()) {
                            inventory = player.getInventory();
                            boolean itemExists = false;

                            for (Item i : inventory) {
                                if (i.getNome().equalsIgnoreCase(item.getNome())) {
                                    itemExists = true;
                                    break;
                                }
                            }

                            if (!itemExists) {
                                player.addItemToInventory(item);
                                System.out.println("Item " + item.getNome() + " adicionado ao inventário.");
                            } else {
                                System.out.println("Item " + item.getNome() + " já está no inventário.");
                            }
                        } else {
                            System.out.println("Item " + item.getNome() + " não é carregável.");
                        }
                    } else {
                        System.out.println("Item com o nome '" + arguments.trim() + "' não encontrado.");
                    }
                    break;
                    case "HELP":
                    // Exibe a lista de comandos
                    System.out.println("Lista de comandos: USE item WITH item, GET, HELP, CHECK, INVENTORY, RESTART, SAIR");
                    break;
                case "INVENTORY":
                    // Acessar o inventário
                    System.out.println("Inventário:");
                    inventory = getInventoryForPlayer(player.getId()); // Agora a variável já está declarada
                    if (inventory.isEmpty()) {
                        System.out.println("O inventário está vazio.");
                    } else {
                        for (Item i : inventory) {
                            System.out.println(i.getNome() + ": " + i.getDescricao());
                        }
                    }
                    break;
                    case "RESTART":
                    // reiniciar o jogo
                    resetGame(conn);
                    System.out.println("Reiniciando o jogo...");
                        Cena cena = cenaDAO.getCenaById(player.getCurrentSceneId());
                        System.out.println("Descrição da cena: " + cena.getDescricao());
                    break;
                case "SAIR":
                    // Encerra o programa
                    System.out.println("Saindo do jogo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Comando inválido.");
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void resetGame(Connection conn) throws SQLException {
        // Zerar a tabela inventory
        String deleteInventorySql = "DELETE FROM Inventory WHERE Id_player = ?";
        try (PreparedStatement deleteInventoryStmt = conn.prepareStatement(deleteInventorySql)) {
            deleteInventoryStmt.setInt(1, player.getId());
            deleteInventoryStmt.executeUpdate();
        }

        // Atualizar a cena atual para cena 1
        String updateSceneSql = "UPDATE usuarios SET cena_id = ? WHERE login = ?";
        try (PreparedStatement updateSceneStmt = conn.prepareStatement(updateSceneSql)) {
            updateSceneStmt.setInt(1, 1); // Cena inicial
            updateSceneStmt.setString(2, player.getLogin());
            updateSceneStmt.executeUpdate();
        }

        // Atualizar a cena atual no objeto Player
        player.setCurrentSceneId(1);
        player.clearInventory(); // Limpar inventário no objeto Player
    }

    public List<Item> getInventoryForPlayer(int playerId) {
        List<Item> inventory = new ArrayList<>();
        String sql = "SELECT i.Id_itens, i.Nome, i.Descricao FROM inventory inv JOIN items i ON inv.Id_item = i.Id_itens WHERE inv.Id_player = ?";

        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, playerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int itemId = rs.getInt("Id_itens");
                    String nome = rs.getString("Nome");
                    String descricao = rs.getString("Descricao");

                    Item item = new Item(itemId, nome, descricao, true);
                    inventory.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventory;
    }

}
