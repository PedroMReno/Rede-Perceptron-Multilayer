import generico.utils.CamadaNeuralUtils;
import generico.RedeNeural;
import generico.dto.Amostra;
import util.DataSplitter;

import java.util.stream.Collectors;

public class MainCaracteres {
    public static void main(String[] args) {
        final var dataTrei = DataSplitter.splitData("C:/Users/Pedro/Desktop/Workspace/IA/RedesNeurais/ProjetoBase/src/data/caracteres-ruido.csv", 63);
        final var dataValid = DataSplitter.splitData("C:/Users/Pedro/Desktop/Workspace/IA/RedesNeurais/ProjetoBase/src/data/caracteres-ruido20.csv", 63);
        final var dataTest = DataSplitter.splitData("C:/Users/Pedro/Desktop/Workspace/IA/RedesNeurais/ProjetoBase/src/data/caracteres-limpo.csv", 63);

        final var rede = new RedeNeural(63);
        rede.addCamada(CamadaNeuralUtils.camadaDePerceptrons(15, true));
        rede.addCamada(CamadaNeuralUtils.camadaDePerceptrons(7, true)); // saida

        rede.fit(dataTrei, dataValid, 0.005, 0.1);

        final var inputs = dataTest.stream().map(Amostra::getInput).collect(Collectors.toList());
        for(int i = 0; i < 7; i++) {
            final var r = rede.predict(inputs.get(i));
            System.out.println(r);
        }
    }
}
