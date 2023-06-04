package percepton;

import generico.dto.Input;
import generico.neuronios.Neuronio;

public class Perceptron extends Neuronio {
    @Override
    protected double computarEntradas() {
        double computada = 0;

        for(Input input : inputBuffer)
            computada += input.getValorPuro() * input.getPeso();

        return computada;
    }

    @Override
    protected double funcaoDeAtivacao(double entradaComputada) {
        final double r = 1 / (1 + Math.pow(Math.E, -entradaComputada));
        return r * 2 - 1;
    }

    @Override
    protected double derivadaDaFuncaoDeAtivacao(double entradaComputada) {
        final var fx = 1 / (1 + Math.pow(Math.E, -entradaComputada));
        return 2 * fx * (1 - fx);
    }
}
