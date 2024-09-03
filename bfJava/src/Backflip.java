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
                        /* return Cmd_use();*/ break;
                    case "GET":
                        /* return Cmd_get();*/ break;
                    case "HELP":
                        System.out.println("USE:comando usado para usar item;\n" +
                                           "GET:comando usado para pegar um item;\n"+
                                           "HELP:comando usado para acessar a lista de comandos;\n"+
                                           "CHECK:comando usado para inspecionar um item ou objeto do cenario;\n"+
                                           "INVENTORY:comando usado para acesar o inventario;\n"+
                                           "SAVE:comando usado salvar seu progresso;\n"+
                                           "LOAD:comando usado carregar seu progresso salvo;\n"+
                                           "RESTART:comando usado para reiniciar o jogo;\n"+
                                           "SAIR:para o jogo;\n"
                        );
                        break;
                    case "CHECK":
                        /*return Cmd_check();*/ break;
                    case "INVENTORY":
                        /*return Cmd_inv();*/ break;
                    case "SAVE":
                        /*return Cmd_save();*/ break;
                    case "LOAD":
                        /*return Cmd_load();*/ break;
                    case "RESTART":
                        /*return Cmd_res();*/ break;
                    case "SAIR":
                        loop = true ;break;
                    default:
                        System.out.println("INVALID COMMAND");
                }
            }


        }


}
