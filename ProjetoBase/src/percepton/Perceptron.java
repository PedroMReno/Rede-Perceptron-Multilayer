package percepton;

import generico.Input;
import generico.Neuronio;

public class Perceptron extends Neuronio {
    @Override
    protected double computarEntradas() {
        double computada = 0;

        for(Input input : inputBuffer)
            computada += input.getValorPuro() * input.getPeso();

        return computada;
    }

    @Override
    protected double efetuarCalculo(double entradaComputada) {
        if (entradaComputada >= 0)
            return 1;

        return 0;
    }
}
