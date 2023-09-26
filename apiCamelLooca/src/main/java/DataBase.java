import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DataBase {
    Connection conectar() {
        Connection conexao = null;
        Boolean bdNuvem = true;
        String url;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if (bdNuvem) {
                url="jdbc:mysql://camel-looca.mysql.database.azure.com:3306/Camel?useSSL=true";
                conexao =DriverManager.getConnection(url, "aidminCamel", "CamelLooca1");
            } else {
          url = "jdbc:mysql://localhost/Camel?user=aidmin&password=aidmin";
            conexao = DriverManager.getConnection(url);
            }
        } catch (SQLException error) {
            System.out.println("Erro ao conectar ao banco de dados: " + error.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Erro: Driver JDBC do MySQL não encontrado.");
        }
        return conexao;
    }
    void inserirRam(Connection conexao,Double memoriaTotal, Double memoriaEmUso,Double memoriaDispinivel){
        try {
            String insercao = "INSERT INTO memoriaram (memoriaTotal, memoriaEmUso, memoriaDisponivel) VALUES (?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(insercao);
            ps.setDouble(1, memoriaTotal);
            ps.setDouble(2, memoriaEmUso);
            ps.setDouble(3, memoriaDispinivel);
            ps.executeUpdate();
        } catch (SQLException error) {
            System.out.println("Erro ao inserir dados no banco de dados:" + error);
        }
    }
    void inserirDisco(Connection conexao,String nomeDisco,String modeloDisco, Double tamanhoDisco, Long velocidadeLeitura, Long leituras, Long velocidadeEscrita,Long escritas, Long fila,Long tempoTrasferencia){
        try {
            String insercao =  "INSERT INTO memoriaDeDisco (nomeDisco,modeloDisco,tamanhoDisco,velocidadeLeitura ,leituras,velocidadeEscrita,escritas,fila, tempoTrasferencia) VALUES (?, ?, ?,?, ?, ?,?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(insercao);
            ps.setString(1,nomeDisco);
            ps.setString(2,modeloDisco);
            ps.setDouble(3,tamanhoDisco);
            ps.setLong(4,velocidadeLeitura);
            ps.setLong(5,leituras);
            ps.setLong(6,velocidadeEscrita);
            ps.setLong(7,escritas);
            ps.setLong(8,fila);
            ps.setLong(9,tempoTrasferencia);
            ps.executeUpdate();
        } catch (SQLException error) {
            System.out.println("Erro ao inserir dados no banco de dados:" + error);
        }
    }
    void inserirRede(Connection conexao,String hostName,String nomeDeDonimio, String dns){
        try {
            String insercao =  "INSERT INTO placaDeRede (hostName,nomeDeDominio,dns) VALUES (?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(insercao);
            ps.setString(1,hostName);
            ps.setString(2,nomeDeDonimio);
            ps.setString(3,dns);
            ps.executeUpdate();
        } catch (SQLException error) {
            System.out.println("Erro ao inserir dados no banco de dados:" + error);
        }
    }
    void inserirProcessador(Connection conexao,String fabricante, String nomeProcessador, Integer numCPUsFisicas , Integer numCPUsLogicas , Double qtdEmUSo, Long frequancia ){
        try {
            String insercao =  "INSERT INTO processador (fabricante,nomeProcessador,numCPUsFisicas, numCPUsLogicas, qtdEmUso, frequencia) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conexao.prepareStatement(insercao);
            ps.setString(1,fabricante);
            ps.setString(2,nomeProcessador);
            ps.setInt(3,numCPUsFisicas);
            ps.setInt(4,numCPUsLogicas);
            ps.setDouble(5,qtdEmUSo);
            ps.setLong(6,frequancia);
            ps.executeUpdate();
        } catch (SQLException error) {
            System.out.println("Erro ao inserir dados no banco de dados:" + error);
        }
    }
}