package generico;

import percepton.Perceptron;

import java.util.LinkedList;
import java.util.List;

public class CamadaNeuralUtils {
    public static CamadaNeural camadaDePerceptrons(final int qtd) {
        final List<Neuronio> neuronios = new LinkedList<>();

        for(int i = 0; i < qtd; i++)
            neuronios.add(new Perceptron());

        return new CamadaNeural(neuronios);
    }
}
