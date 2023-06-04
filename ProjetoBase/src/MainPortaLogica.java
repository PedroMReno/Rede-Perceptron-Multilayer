import generico.RedeNeural;
import generico.dto.Amostra;
import generico.utils.CamadaNeuralUtils;
import generico.utils.ConfusionMatrixUtils;
import util.DataSplitter;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class MainPortaLogica {
    public static void main(String[] args) {
        final var data = DataSplitter.splitData("C:/Users/Pedro/Desktop/Workspace/IA/RedesNeurais/ProjetoBase/src/data/problemXOR.csv", 2);

        final var rede = new RedeNeural(2);
        rede.addCamada(CamadaNeuralUtils.camadaDePerceptrons(2, true));
        rede.addCamada(CamadaNeuralUtils.camadaDePerceptrons(1, true)); // saida

        rede.fit(data, 0.005, 100000);

        final var inputs = data.stream().map(Amostra::getInput).collect(Collectors.toList());
        final var expected = data.stream().map(in -> in.getExpect().get(0)).collect(Collectors.toList());
        final var result = new LinkedList<Double>();
        inputs.forEach(input -> result.add(rede.predict(input).get(0)));
        ConfusionMatrixUtils.confusionMatrixForBinary(result, expected);
    }
}
