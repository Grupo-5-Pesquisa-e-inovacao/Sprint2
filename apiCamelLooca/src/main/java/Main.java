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
//        Connection conexaoNuvem = db.conectarNuvem();
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
                    db.inserirRam(conexao, ram.getMemoriaTotal(), ram.getMemoriaEmUso());
                    db.inserirDisco(conexao, disco.getNome(), disco.getTamanho(), disco.getUsoDisco());
                   db.inserirRede(conexao, rede.getHostName(), rede.getNumIpv4(), rede.getBytesRecebidos(), rede.getBytesEnviados());
                    db.inserirProcessador(conexao,processador.getNome(),processador.getQtdemUso(), processador.getFrequencia());

//                    db.inserirRam(conexaoNuvem, ram.getMemoriaTotal(), ram.getMemoriaEmUso(), ram.getMemoriaDisponivel());
//                    db.inserirDisco(conexaoNuvem, disco.getNome(), disco.getModelo(), disco.getTamanho(), disco.getVelocidadeLeitura(), disco.getLeituras(), disco.getVelocidadeEscrita(), disco.getEscritas(), disco.getFila(), disco.getTempoTransferencia());
//                    db.inserirRede(conexaoNuvem, rede.getHostName(), rede.getNomeDeDominio(), Arrays.toString(rede.getServidoresDNS()));
//                    db.inserirProcessador(conexaoNuvem, processador.getFabricante(), processador.getNome(), processador.getNumCpuFisica(), processador.getNumCpuLogica(), processador.getQtdemUso(), processador.getFrequencia());
                }
            }, i * 30000);
        }
    }
}
