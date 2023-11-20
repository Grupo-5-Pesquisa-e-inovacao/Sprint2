import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DataBase {
    Connection conectar() {
        Connection conexao = null;
        String url;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost:3306/CamelTech?user=aidmin&password=senhaDificil235813";
            conexao = DriverManager.getConnection(url);

        } catch (SQLException error) {
            System.out.println("Erro ao conectar ao banco de dados local: " + error.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Erro: Driver JDBC do MySQL não encontrado.");
        }
        return conexao;
    }

    Connection conectarNuvem() {
        Connection conexao = null;
        Boolean bdNuvem = true;
        String url;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            url = "jdbc:mysql://containers-us-west-156.railway.app:6470/railway";
            conexao = DriverManager.getConnection(url, "root", "Utjrg0FbyRsc68BFOQC3");

        } catch (SQLException error) {
            System.out.println("Erro ao conectar ao banco de dados: " + error.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Erro: Driver JDBC do MySQL não encontrado.");
        }
        return conexao;
    }

    void inserir(Connection conexao, String nomeProcessador, Double usoDisco, Double tamanhoDisco, String nomeDisco, Double qtdEmUso, Long frequencia, Double totalRam, Double emUsoRam, String hostName, String numIpv4, Long bytesRecebidos, Long bytesEnviados) {
        try {
            String insercao = "INSERT INTO dadosHardware (nomeProcessador,usoDisco,tamanhoDisco,nomeDisco,qtdEmUso,frequencia,totalRam,emUsoRam,hostName,numIpv4,bytesRecebidos,bytesEnviados) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(insercao);

            ps.setString(1, nomeProcessador);
            ps.setDouble(2, usoDisco);
            ps.setDouble(3, tamanhoDisco);
            ps.setString(4, nomeDisco);
            ps.setDouble(5, qtdEmUso);
            ps.setLong(6, frequencia);
            ps.setDouble(7, totalRam);
            ps.setDouble(8, emUsoRam);
            ps.setString(9, hostName);
            ps.setString(10, numIpv4);
            ps.setLong(11, bytesRecebidos);
            ps.setLong(12, bytesEnviados);

            ps.executeUpdate();
        } catch (SQLException error) {
            System.out.println("Erro ao inserir dados no banco de dados: " + error);
        }
    }
}

