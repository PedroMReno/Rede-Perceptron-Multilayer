package generico.neuronios;

import generico.neuronios.Neuronio;

public class NeuronioSensorial extends Neuronio {
    @Override
    protected double computarEntradas() {
        if(inputBuffer.size() == 0) {
            throw new RuntimeException("Quantidade de entradas inesperada");
        }

        return inputBuffer.get(0).getValorPuro();
    }

    @Override
    protected double funcaoDeAtivacao(double entradaComputada) {
        return entradaComputada;
    }

    @Override
    protected double derivadaDaFuncaoDeAtivacao(double entradaComputada) {
        return entradaComputada;
    }
}
