import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;
public class PlacaDeRede {
    private String hostName;
    private String nomeDeDominio;
    private String[] servidoresDNS;
    private String texto;
    String  captarPlacaDeRede() {
        Looca looca= new Looca();
        Rede rede = looca.getRede();
        texto = String.valueOf(rede.getParametros());
        return texto;
    }
    public void exibirRelatorio(String texto) {
        String[] linhas = texto.split("\n");
        for (String linha : linhas) {
            if (linha.startsWith("Hostname:")) {
                hostName = linha.substring("Hostname: ".length()).trim();
            } else if (linha.startsWith("Nome de domínio:")) {
                nomeDeDominio = linha.substring("Nome de domínio: ".length()).trim();
            } else if (linha.startsWith("Servidores DNS:")) {
                String dnsInfo = linha.substring("Servidores DNS: ".length()).trim();
                // Remova os colchetes e separe os IPs por vírgula e espaço
                dnsInfo = dnsInfo.substring(1, dnsInfo.length() - 1);
                servidoresDNS = dnsInfo.split(", ");
                for (int i = 0; i < servidoresDNS.length; i++) {
                    servidoresDNS[i] = servidoresDNS[i].trim();
                }
            }
        }
        System.out.println("HostName: " + hostName);
        System.out.println("Nome do Domínio: " + nomeDeDominio);
        System.out.print("Servidores DNS: ");
        if (servidoresDNS != null) {
            for (String dns : servidoresDNS) {
                System.out.print(dns + " ");
            }
        }
    }
    public String getHostName() {
        return hostName;
    }
    public String getNomeDeDominio() {
        return nomeDeDominio;
    }
    public String[] getServidoresDNS() {
        return servidoresDNS;
    }
    public String getTexto() {
        return texto;
    }
}