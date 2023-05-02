import generico.utils.CamadaNeuralUtils;
import generico.RedeNeural;
import generico.dto.InputTreinamento;
import generico.utils.ConfusionMatrixUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        final var rede = new RedeNeural(2);
        rede.addCamada(CamadaNeuralUtils.camadaDePerceptrons(4, true)); // saida
        rede.addCamada(CamadaNeuralUtils.camadaDePerceptrons(1, true)); // saida

        rede.fit(geraDados(), 0.005, 1000);

        final var inputs = geraDados().stream().map(InputTreinamento::getInput).collect(Collectors.toList());
        final var expected = geraDados().stream().map(in -> in.getExpect().get(0)).collect(Collectors.toList());
        final var result = new LinkedList<Double>();
        inputs.forEach(input -> result.add(rede.predict(input).get(0)));
        ConfusionMatrixUtils.confusionMatrixForBinary(result, expected);
    }

    private static List<InputTreinamento> geraDados() {
        return List.of(
                new InputTreinamento(List.of(-1.0, -1.0), List.of(-1.0)),
                new InputTreinamento(List.of(1.0, -1.0), List.of(-1.0)),
                new InputTreinamento(List.of(-1.0, 1.0), List.of(-1.0)),
                new InputTreinamento(List.of(1.0, 1.0), List.of(1.0))
        );
    }
}
