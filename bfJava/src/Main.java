import model.Cena;
import repository.CenaDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CenaDAO cenaDAO = new CenaDAO();
        Player player = null;

        // Fluxo de registro ou login
        while (player == null) {
            System.out.println("Escolha uma opção: [1] Registrar [2] Login");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1": // Registrar novo usuário
                    System.out.println("Digite o login desejado:");
                    String novoLogin = scanner.nextLine();
                    System.out.println("Digite a senha desejada:");
                    String novaSenha = scanner.nextLine();

                    try {
                        Registro.registrar(novoLogin, novaSenha);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("Erro ao registrar o usuário.");
                    }
                    break;

                case "2": // Login existente
                    System.out.println("Digite seu login:");
                    String login = scanner.nextLine();
                    System.out.println("Digite sua senha:");
                    String senha = scanner.nextLine();

                    player = Login.realizarLogin(login, senha);
                    if (player == null) {
                        System.out.println("Falha no login. Tente novamente.");
                    }
                    break;

                default:
                    System.out.println("Opção inválida. Escolha 1 ou 2.");
            }
        }


        Cena cena = cenaDAO.getCenaById(player.getCurrentSceneId());
        if (cena != null) {
            System.out.println("Descrição da cena: " + cena.getDescricao());
        } else {
            System.out.println("Cena não encontrada.");
        }

        // executor dos comandos
        GameSave gameSave = new GameSave();
        CommandExecutor commandExecutor = new CommandExecutor(player, gameSave);

        //Loop Principal
        while (true) {
            System.out.println("Digite um comando:");
            String input = scanner.nextLine();

            // Remove espaços em branco e divide a entrada em partes
            String[] parts = input.trim().split(" +");

            if (parts.length == 1) {
                // Comando Único
                String comando1 = parts[0].toUpperCase();
                commandExecutor.executeCommand(comando1, "", "", "");
            } else if (parts.length == 2) {
                // Comando com um item
                String comando1 = parts[0].toUpperCase();
                String argumento1 = parts[1];
                commandExecutor.executeCommand(comando1, "", argumento1, "");
            } else if (parts.length == 4 && parts[2].equalsIgnoreCase("WITH")) {
                // Comando com dois itens
                String comando1 = parts[0].toUpperCase();
                String item1 = parts[1];
                String item2 = parts[3];
                commandExecutor.executeCommand(comando1, "", item1, item2);
            } else {
                System.out.println("Comando inválido.");
            }
        }
    }
}