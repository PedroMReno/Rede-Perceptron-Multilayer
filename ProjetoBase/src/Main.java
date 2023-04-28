import generico.CamadaNeural;
import generico.CamadaNeuralUtils;
import generico.RedeNeural;
import generico.dto.InputTreinamento;
import percepton.Perceptron;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        final var rede = new RedeNeural(2);
        rede.addCamada(CamadaNeuralUtils.camadaDePerceptrons(8, true));
        rede.addCamada(CamadaNeuralUtils.camadaDePerceptrons(1, true)); // saida

        rede.fit(geraDados(), 0.001, 10000);

        System.out.println(rede.predict(List.of(-1.0, -1.0)));
        System.out.println(rede.predict(List.of(1.0, -1.0)));
        System.out.println(rede.predict(List.of(-1.0, 1.0)));
        System.out.println(rede.predict(List.of(1.0, 1.0)));
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
