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
        return Math.tanh(entradaComputada);
    }

    @Override
    protected double derivadaDaFuncaoDeAtivacao(double entradaComputada) {
        return 1 / (1 - Math.pow(entradaComputada, 2));
    }
}
