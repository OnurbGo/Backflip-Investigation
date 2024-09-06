import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CenaDAO cenaDAO = new CenaDAO();
        Player player = null;

        System.out.println("Bem-vindo ao jogo!");

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

        // Exibe a cena atual após o login bem-sucedido
        Cena cena = cenaDAO.getCenaById(player.getCurrentSceneId());
        if (cena != null) {
            System.out.println("Descrição da cena: " + cena.getDescricao());
        } else {
            System.out.println("Cena não encontrada.");
        }

        // Inicializa o executor de comandos
        CommandExecutor commandExecutor = new CommandExecutor(player);

        // Loop principal de comandos do jogo
        while (true) {
            System.out.println("Digite um comando:");
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0].toUpperCase();
            String arguments = (parts.length > 1) ? parts[1] : "";

            commandExecutor.executeCommand(command, arguments);
        }
    }
}
