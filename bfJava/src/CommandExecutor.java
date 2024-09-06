public class CommandExecutor {
    private Player player;
    private ItemDAO itemDAO = new ItemDAO(); // Inicialize ItemDAO

    public CommandExecutor(Player player) {
        this.player = player;
    }

    public void executeCommand(String command, String arguments) {
        switch (command) {
            case "USE":
                // Lógica para usar um item
                System.out.println("Usando item: " + arguments);
                break;
            case "GET":
                // Lógica para pegar um item
                Item item = itemDAO.getItemByName(arguments.trim()); // Use o nome do item
                if (item != null && item.isCarregavel()) { // Corrigido para isLoadable()
                    player.addItemToInventory(item);
                    System.out.println("Item " + item.getName() + " adicionado ao inventário."); // Corrigido para getName()
                } else {
                    System.out.println("Item não carregável ou não encontrado.");
                }
                break;
            case "HELP":
                // Exibe a lista de comandos
                System.out.println("Lista de comandos: USE, GET, HELP, CHECK, INVENTORY, SAVE, LOAD, RESTART, SAIR");
                break;
            case "CHECK":
                // Lógica para inspecionar um item ou objeto
                System.out.println("Inspecionando: " + arguments);
                break;
            case "INVENTORY":
                // Lógica para acessar o inventário
                System.out.println("Inventário:");
                System.out.println("Inventário do jogador:");
                for (Item i : player.getInventory()) {
                    System.out.println(i.getName()); // Corrigido para getName()
                }
                break;
            case "SAVE":
                // Lógica para salvar o progresso
                GameSave.saveGame(player);
                System.out.println("Progresso salvo.");
                break;
            case "LOAD":
                // Lógica para carregar o progresso
                GameSave.loadGame(player);
                System.out.println("Progresso carregado.");
                break;
            case "RESTART":
                // Lógica para reiniciar o jogo
                System.out.println("Reiniciando o jogo...");
                break;
            case "SAIR":
                // Encerra o programa
                System.out.println("Saindo do jogo...");
                System.exit(0); // Adiciona uma instrução para parar o código
                break;
            default:
                System.out.println("Comando inválido.");
                break;
        }
    }
}
