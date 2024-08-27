import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Backflip {

        public static void main(String[] args) {

            String cmd = "";
            Scanner sc = new Scanner(System.in);
            boolean loop = false;

            while (loop != true){
                System.out.println("Digite um comando:");
                cmd = sc.nextLine();
                switch (cmd){
                    case "USE":
                        /* return cmd_use();*/ break;
                    case "GET":
                        /* return cmd_get();*/ break;
                    case "HELP":
                        System.out.println("USE:comando usado para usar item;\n" +
                                           "GET:comando usado para pegar um item;\n"+
                                           "HELP:comando usado para acessar a lista de comandos;\n"+
                                           "CHECK:comando usado para inspecionar um item ou objeto do cenario;\n"+
                                           "INVENTORY:comando usado para acesar o inventario;\n"+
                                           "SAVE:comando usado salvar seu progresso;\n"+
                                           "LOAD:comando usado carregar seu progresso salvo;\n"+
                                           "RESTART:comando usado para reiniciar o jogo;\n"
                        );
                        break;
                    case "CHECK":
                        /*return cmd_check();*/ break;
                    case "INVENTORY":
                        /*return cmd_inv();*/ break;
                    case "SAVE":
                        /*return cmd_save();*/ break;
                    case "LOAD":
                        /*return cmd_load();*/ break;
                    case "RESTART":
                        /*return cmd_res();*/ break;
                    default:
                        System.out.println("INVALID COMMAND");
                }
            }


        }

/*        public static Connection conectadb() throws ClassNotFoundException{
            try {
                Class.forName("org.qjt.mm.mysql.Driver");
                Connection con = DriverManager.getConnection("jdbc:Mysql://localhost:3306/bancodedados1","root");
                return con;
            } catch (SQLException var1) {
                JOptionPane.showMessageDialog((Component)null, "Erro de conexão com mySQL");
                return null;
            }
        }
*/



}
