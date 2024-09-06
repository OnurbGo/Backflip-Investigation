import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PessoaDAO {

    public Pessoa getPessoaById(int pessoaId) {
        String sql = "SELECT * FROM pessoas WHERE id = ?";
        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pessoaId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Certifique-se de que os nomes das colunas e os tipos de dados estão corretos
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String descricao = rs.getString("descricao");

                    return new Pessoa(id, nome, descricao);
                } else {
                    System.out.println("Pessoa não encontrada.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
