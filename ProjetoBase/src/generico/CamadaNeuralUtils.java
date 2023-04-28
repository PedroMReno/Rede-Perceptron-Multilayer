package generico;

import generico.neuronios.Neuronio;
import percepton.Perceptron;

import java.util.LinkedList;
import java.util.List;

public class CamadaNeuralUtils {
    public static CamadaNeural camadaDePerceptrons(final int qtd, final boolean comBias) {
        final List<Neuronio> neuronios = new LinkedList<>();

        for(int i = 0; i < qtd; i++)
            neuronios.add(new Perceptron());

        return new CamadaNeural(neuronios, comBias);
    }
}
