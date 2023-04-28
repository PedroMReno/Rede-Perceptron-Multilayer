package generico;

import generico.dto.Input;
import generico.neuronios.Bias;
import generico.neuronios.Neuronio;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CamadaNeural {
    private final List<Neuronio> neuronios;
    private final Neuronio bias;

    public CamadaNeural(final List<Neuronio> neuronios) {
        this(neuronios, false);
    }

    public CamadaNeural(final List<Neuronio> neuronios, final boolean comBias) {
        this.neuronios = neuronios;

        if (comBias) {
            this.bias = new Bias();

            final var gerador = new Random();
            this.neuronios.forEach(neuronio -> bias.addVizinhoSeguinte(neuronio, pesoValidoAleatorio(gerador)));
        } else {
            this.bias = null;
        }
    }

    public void feedForwarding() {
        if (bias != null)
            bias.feedforward();

        neuronios.forEach(Neuronio::feedforward);
    }

    public void ligarTotalmenteCom(final CamadaNeural seguinte) {
        final var gerador = new Random();

        neuronios.forEach(atual ->
                seguinte.neuronios.forEach(viz -> atual.addVizinhoSeguinte(viz, pesoValidoAleatorio(gerador)))
        );
    }

    private double pesoValidoAleatorio(final Random r) {
        final var v = (r.nextDouble() + 0.1) % 1;
        return v * (r.nextBoolean() ? 1 : -1);
    }

    public void addEntrada(final List<Double> entrada) {
        if(entrada.size() != neuronios.size())
            throw new RuntimeException("Tamanho de entrada invalido");

        final var iterNeu = neuronios.iterator();
        for (Double e : entrada) {
            iterNeu.next().addInput(new Input(e));
        }
    }

    public List<Double> getOutput() {
        return neuronios.stream().map(Neuronio::getUltimoValorCalculado).collect(Collectors.toList());
    }

    public void backPropagation(final double taxaDeAprendizado) {
        this.neuronios.forEach(neu -> neu.backPropagation(taxaDeAprendizado));
    }

    public void aplicarDeltaPesos() {
        this.neuronios.forEach(Neuronio::aplicarDeltaPesos);
    }
}
