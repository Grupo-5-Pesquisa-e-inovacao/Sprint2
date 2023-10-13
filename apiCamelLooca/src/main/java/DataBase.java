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
          url = "jdbc:mysql://localhost/Cameltech?user=aidmin&password=aidmin";
            conexao = DriverManager.getConnection(url);

        } catch (SQLException error) {
            System.out.println("Erro ao conectar ao banco de dados: " + error.getMessage());
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

                url="jdbc:mysql://camel-looca.mysql.database.azure.com:3306/Camel?useSSL=true";
                conexao =DriverManager.getConnection(url, "aidminCamel", "CamelLooca1");

        } catch (SQLException error) {
            System.out.println("Erro ao conectar ao banco de dados: " + error.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Erro: Driver JDBC do MySQL não encontrado.");
        }
        return conexao;
    }
    void inserirRam(Connection conexao,Double memoriaTotal, Double memoriaEmUso){
        try {
            String insercao = "INSERT INTO dadosHardware (totalRam, emUsoRam) VALUES (?, ?)";
            PreparedStatement ps = conexao.prepareStatement(insercao);
            ps.setDouble(1, memoriaTotal);
            ps.setDouble(2, memoriaEmUso);
            ps.executeUpdate();
        } catch (SQLException error) {
            System.out.println("Erro ao inserir dados no banco de dados:" + error);
        }
    }
    void inserirDisco(Connection conexao,String nomeDisco, Double tamanhoDisco, Double usoDisco){
        try {
            String insercao =  "INSERT INTO dadosHardware (nomeDisco,tamanhoDisco,usoDisco) VALUES (?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(insercao);
            ps.setString(1,nomeDisco);
            ps.setDouble(2,tamanhoDisco);
            ps.setDouble(3,usoDisco);
            ps.executeUpdate();
        } catch (SQLException error) {
            System.out.println("Erro ao inserir dados no banco de dados:" + error);
        }
    }
    void inserirRede(Connection conexao, String hostName, String numIpv4, Long bytesRecebidos, Long bytesEnviados){
        try {
            String insercao =  "INSERT INTO dadosHardware (hostName,numIpv4,bytesRecebidos,bytesEnviados) VALUES (?, ?, ?,?)";
            PreparedStatement ps = conexao.prepareStatement(insercao);
            ps.setString(1,hostName);
            ps.setString(2,numIpv4);
            ps.setLong(3,bytesRecebidos);
            ps.setLong(4,bytesEnviados);
            ps.executeUpdate();
        } catch (SQLException error) {
            System.out.println("Erro ao inserir dados no banco de dados:" + error);
        }
    }
    void inserirProcessador(Connection conexao, String nomeProcessador,Double qtdEmUSo, Long frequancia ){
        try {
            String insercao =  "INSERT INTO dadosHardware (nomeProcessador,qtdEmUso,frequencia) VALUES (?,?,?)";
            PreparedStatement ps = conexao.prepareStatement(insercao);
            ps.setString(1,nomeProcessador);
            ps.setDouble(2,qtdEmUSo);
            ps.setLong(3,frequancia);
            ps.executeUpdate();
        } catch (SQLException error) {
            System.out.println("Erro ao inserir dados no banco de dados:" + error);
        }
    }
}