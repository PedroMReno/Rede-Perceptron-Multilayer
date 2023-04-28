package generico.neuronios;

public class Bias extends Neuronio {
    @Override
    protected double computarEntradas() {
        return 1;
    }

    @Override
    protected double funcaoDeAtivacao(double entradaComputada) {
        return 1;
    }

    @Override
    protected double derivadaDaFuncaoDeAtivacao(double entradaComputada) {
        return 1;
    }
}
