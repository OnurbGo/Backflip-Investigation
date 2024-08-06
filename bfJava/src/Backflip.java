import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Backflip {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Digite seu nome: ");
            String nome = sc.nextLine();
            System.out.println("Seu nome é:" + nome);
        }

//        public static Connection conectadb() throws ClassNotFoundException{
//            try {
//                Class.forName("org.qjt.mm.mysql.Driver");
//                Connection con = DriverManager.getConnection("jdbc:Mysql://localhost:3306/bancodedados1","root");
//                return con;
//            } catch (SQLException var1) {
//                JOptionPane.showMessageDialog((Component)null, "Erro de conexão com mySQL");
//                return null;
//            }
//        }


}
