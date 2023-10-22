import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
public class Main {
    public static void main(String[] args) {




        MemoriaDeDisco disco = new MemoriaDeDisco();
        MemoriaRam ram = new MemoriaRam();
        PlacaDeRede rede = new PlacaDeRede();
        PuxarProcessador processador = new PuxarProcessador();
        DataBase db = new DataBase();
        Timer timer = new Timer();
        Connection conexao = db.conectar();
        Connection conexaoNuvem = db.conectarNuvem();
        System.out.println("Inicio do processo de captura");
        System.out.println("Tempo total de captura: 50m");
        for (int i = 0; i < 100; i++) {
            timer.schedule(new TimerTask() {
                public void run() {
                    LocalDateTime dataHora = LocalDateTime.now();
                    DateTimeFormatter formatadorDeDataHora = DateTimeFormatter.ofPattern("hh:mm:ss", Locale.forLanguageTag("pt-BR"));
                    System.out.println(formatadorDeDataHora.format(dataHora));
                    disco.captarInformacoesDoDisco();
                    ram.captarMemoria();
                    rede.captarPlacaDeRede();
                    processador.captarProcessador();

                    db.inserir(conexao, processador.getNome(), disco.getUsoDisco(), disco.getTamanho(), disco.getNome(), disco.getUsoDisco(), processador.getFrequencia(), ram.getMemoriaTotal(), ram.getMemoriaEmUso(), rede.getHostName(), rede.getNumIpv4(), rede.getBytesRecebidos(), rede.getBytesEnviados());

                    disco.captarInformacoesDoDisco();
                    ram.captarMemoria();
                    rede.captarPlacaDeRede();
                    processador.captarProcessador();
                   db.inserir(conexaoNuvem, processador.getNome(), disco.getUsoDisco(), disco.getTamanho(), disco.getNome(), disco.getUsoDisco(), processador.getFrequencia(), ram.getMemoriaTotal(), ram.getMemoriaEmUso(), rede.getHostName(), rede.getNumIpv4(), rede.getBytesRecebidos(), rede.getBytesEnviados());
                }
            }, i * 30000);
        }
    }
}
