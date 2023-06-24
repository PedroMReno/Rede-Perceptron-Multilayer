import util.CamadaNeuralUtils;
import generico.RedeNeural;
import generico.dto.Amostra;
import util.ConfusionMatrixUtils;
import util.DataSplitter;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MainCaracteres {
    public static void main(String[] args) {
        final var dataTrei = DataSplitter.splitData("C:/Users/Pedro/Desktop/Workspace/IA/RedesNeurais/ProjetoBase/src/data/caracteres-ruido.csv", 63);
        final var dataValid = DataSplitter.splitData("C:/Users/Pedro/Desktop/Workspace/IA/RedesNeurais/ProjetoBase/src/data/caracteres-ruido20.csv", 63);
        final var dataTest = DataSplitter.splitData("C:/Users/Pedro/Desktop/Workspace/IA/RedesNeurais/ProjetoBase/src/data/caracteres-limpo.csv", 63);

        final var rede = new RedeNeural(63);
        rede.addCamada(CamadaNeuralUtils.camadaDePerceptrons(15, true));
        rede.addCamada(CamadaNeuralUtils.camadaDePerceptrons(7, true)); // saida

        rede.fit(dataTrei, dataValid, 0.005, 0.01);

        final var inputs = dataTest.stream().map(Amostra::getInput).collect(Collectors.toList());
        final var expected = dataTest.stream().map(amos -> getHigherIndex(amos.getExpected()))
                .collect(Collectors.toList());
        final List<Integer> results = new LinkedList<>();

        for (List<Double> input : inputs) {
            final var r = rede.predict(input);
            results.add(getHigherIndex(r));

            System.out.println(r);
        }

        System.out.println();

        ConfusionMatrixUtils.confusionMatrixForMultiClass(results, expected, 7);
    }

    private static int getHigherIndex(final List<Double> list) {
        double higher = -999;
        int r = 0; // resposta

        for(int i = 0; i < list.size(); i++) {
            final double value = list.get(i);

            if (higher < value) {
                higher = value;
                r = i;
            }
        }

        return r;
    }
}
