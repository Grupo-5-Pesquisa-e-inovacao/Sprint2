import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import java.util.List;
public class MemoriaDeDisco {
    private String nome;
    private String modelo;
    private Double tamanho;
    private Long velocidadeLeitura;
    private Long leituras;
    private Long escritas;
    private Long velocidadeEscrita;
    private Long fila;
    private Long tempoTransferencia;
    public void captarInformacoesDoDisco() {
        Looca looca = new Looca();
        DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
        List<Disco> discos = grupoDeDiscos.getDiscos();
        for (Disco disco : discos) {
            nome = disco.getNome();
            modelo = disco.getModelo();
            tamanho = disco.getTamanho() / 1073741824.0;
            velocidadeLeitura = disco.getBytesDeLeitura();
            leituras = disco.getLeituras();
            escritas = disco.getEscritas();
            velocidadeEscrita = disco.getBytesDeEscritas();
            fila = disco.getTamanhoAtualDaFila();
            tempoTransferencia = disco.getTempoDeTransferencia();
        }
    }
    public String getNome() {
        return nome;
    }
    public String getModelo() {
        return modelo;
    }
    public Double getTamanho() {
        return tamanho;
    }
    public Long getVelocidadeLeitura() {
        return velocidadeLeitura;
    }
    public Long getLeituras() {
        return leituras;
    }
    public Long getEscritas() {
        return escritas;
    }
    public Long getVelocidadeEscrita() {
        return velocidadeEscrita;
    }
    public Long getFila() {
        return fila;
    }
    public Long getTempoTransferencia() {
        return tempoTransferencia;
    }
}

