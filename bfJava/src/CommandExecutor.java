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
        public void executeCommand(String comando1, String argumento1, String argumento2, String arguments) {
        try (Connection conn = repository.DB.conectar()) {
            List<Item> inventory = null;

            switch (comando1) {
                case "USE":
                    System.out.println("Usando item: " + arguments);

                    // Divide o comando para checar "use [item] with [alvo]" (se necessário)
                    String[] parts = arguments.split(" with ");
                    String itemUsado = parts[0].trim();
                    inventory = player.getInventory();
                    Item itemToRemove = null;
                    boolean hasItem = false;

                    // Verifica se o jogador tem o item no inventário
                    for (Item item : inventory) {
                        if (item.getNome().equalsIgnoreCase(itemUsado)) {
                            hasItem = true;
                            itemToRemove = item;
                            break;
                        }
                    }

                    if (hasItem) {
                        // Caso 1: Cena 3 - Usar "ACUSAR" sem alvo
                        if (itemToRemove.getNome().equalsIgnoreCase("ACUSAR") && player.getCurrentSceneId() == 3) {
                            System.out.println("Parabéns, você capturou o criminoso!");
                            // Aqui você pode adicionar lógica adicional, como finalizar o jogo ou passar para outra fase.

                            // Caso 2: Cena 1 - Usar "MUNICAO_DESCONHECIDO"
                        } else if (itemToRemove.getNome().equalsIgnoreCase("MUNICAO_DESCONHECIDO") && player.getCurrentSceneId() == 1) {
                            // Remove "MUNICAO_DESCONHECIDO" e adiciona "MUNICAO_9MM"
                            player.removeItemFromInventory(itemToRemove);
                            System.out.println("Item MUNICAO_DESCONHECIDO removido do inventário.");

                            Item municao9mm = itemDAO.getItemByName("MUNICAO_9MM");
                            if (municao9mm != null) {
                                player.addItemToInventory(municao9mm);
                                System.out.println("Item MUNICAO_9MM adicionado ao inventário.");
                            } else {
                                System.out.println("Item MUNICAO_9MM não encontrado no banco de dados.");
                            }

                            // Avança para a Cena 2
                            player.setCurrentSceneId(2);
                            GameSave.saveGame(player); // Salva o progresso após mudar a cena
                            Cena cenaAtual = cenaDAO.getCenaById(2); // Obtém a nova cena do banco de dados
                            if (cenaAtual != null) {
                                System.out.println("Você avançou para a Cena 2: " + cenaAtual.getDescricao());
                            } else {
                                System.out.println("Cena 2 não encontrada.");
                            }

                            // Caso 3: Cena 2 - Usar "MUNICAO_9MM"
                        } else if (itemToRemove.getNome().equalsIgnoreCase("MUNICAO_9MM") && player.getCurrentSceneId() == 2) {
                            // Remove "MUNICAO_9MM" e adiciona "ACUSAR"
                            player.removeItemFromInventory(itemToRemove);
                            System.out.println("Item MUNICAO_9MM removido do inventário.");

                            Item acusar = itemDAO.getItemByName("ACUSAR");
                            if (acusar != null) {
                                player.addItemToInventory(acusar);
                                System.out.println("Item ACUSAR adicionado ao inventário.");
                            } else {
                                System.out.println("Item ACUSAR não encontrado no banco de dados.");
                            }

                            // Avança para a Cena 3
                            player.setCurrentSceneId(3);
                            GameSave.saveGame(player); // Salva o progresso após mudar a cena
                            Cena cenaAtual = cenaDAO.getCenaById(3); // Obtém a nova cena do banco de dados
                            if (cenaAtual != null) {
                                System.out.println("Você avançou para a Cena 3: " + cenaAtual.getDescricao());
                            } else {
                                System.out.println("Cena 3 não encontrada.");
                            }

                        } else {
                            // Se o item não for encontrado ou a ação não puder ser realizada
                            System.out.println("Esse item não pode ser usado nesta cena.");
                        }
                    } else {
                        System.out.println("Item " + itemUsado + " não encontrado no inventário.");
                    }
                    break;
                case "GET":
                    System.out.println("Procurando item: " + arguments.trim());
                    Item item = itemDAO.getItemByName(arguments.trim());

                    if (item != null) {
                        // Verifica se o item pertence à cena atual do jogador
                        int currentSceneId = player.getCurrentSceneId();
                        if (item.getId_cena() == currentSceneId) { // Supondo que o item tenha um método getSceneId()

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
                            System.out.println("Você não pode pegar itens de outra cena.");
                        }
                    } else {
                        System.out.println("Item com o nome '" + arguments.trim() + "' não encontrado.");
                    }
                    break;

                case "INVENTORY":
                    // Acessar o inventário
                    System.out.println("Inventário:");
                    inventory = getInventoryForPlayer(player.getId());
                    if (inventory.isEmpty()) {
                        System.out.println("O inventário está vazio.");
                    } else {
                        for (Item i : inventory) {
                            System.out.println(i.getNome() + ": " + i.getDescricao());
                        }
                    }
                    break;
                    case "HELP":
                        System.out.println("RESTART-Resetar Jogo,GET-Pegar Item,USE-Usar item,SAIR-Sair Do Jogo,INVENTORY-Mostrar Inventario,HELP-Mostrar Comandos");
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

        try (Connection conn = repository.DB.conectar();
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
