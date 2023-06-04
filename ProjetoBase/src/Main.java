import generico.utils.CamadaNeuralUtils;
import generico.RedeNeural;
import generico.dto.Amostra;
import generico.utils.ConfusionMatrixUtils;
import util.DataSplitter;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        final var dataTrei = DataSplitter.splitData("C:/Users/Pedro/Desktop/Workspace/IA/RedesNeurais/ProjetoBase/src/data/caracteres-ruido.csv", 63);
        final var dataValid = DataSplitter.splitData("C:/Users/Pedro/Desktop/Workspace/IA/RedesNeurais/ProjetoBase/src/data/caracteres-ruido20.csv", 63);
        final var dataTest = DataSplitter.splitData("C:/Users/Pedro/Desktop/Workspace/IA/RedesNeurais/ProjetoBase/src/data/caracteres-limpo.csv", 63);

        final var rede = new RedeNeural(63);
        rede.addCamada(CamadaNeuralUtils.camadaDePerceptrons(12, true));
        rede.addCamada(CamadaNeuralUtils.camadaDePerceptrons(7, true)); // saida

        rede.fit(dataTrei, dataValid, 0.005, 0.01);

        /*
        final var inputs = data.stream().map(Amostra::getInput).collect(Collectors.toList());
        final var expected = data.stream().map(in -> in.getExpect().get(0)).collect(Collectors.toList());
        final var result = new LinkedList<Double>();
        inputs.forEach(input -> result.add(rede.predict(input).get(0)));
        ConfusionMatrixUtils.confusionMatrixForBinary(result, expected);
         */

        final var inputs = dataTest.stream().map(Amostra::getInput).collect(Collectors.toList());
        for(int i = 0; i < 7; i++) {
            final var r = rede.predict(inputs.get(i));
            System.out.println(r);
        }
    }
}
