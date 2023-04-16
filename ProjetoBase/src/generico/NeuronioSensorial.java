package generico;

import java.util.List;

public class NeuronioSensorial extends Neuronio {
    @Override
    protected double computarEntradas() {
        if(inputBuffer.size() != 1) {
            throw new RuntimeException("Quantidade de entradas inesperada");
        }

        return inputBuffer.get(0).getValorPuro();
    }

    @Override
    protected double efetuarCalculo(double entradaComputada) {
        return entradaComputada;
    }
}
