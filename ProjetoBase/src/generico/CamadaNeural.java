package generico;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CamadaNeural {
    private final List<Neuronio> neuronios;

    public CamadaNeural(final List<Neuronio> neuronios) {
        this.neuronios = neuronios;
    }

    public void ativarNeuronios() {
        neuronios.forEach(Neuronio::feedforward);
    }

    public void ligarTotalmenteCom(final CamadaNeural seguinte) {
        final var gerador = new Random();

        neuronios.forEach(atual ->
                seguinte.neuronios.forEach(viz -> atual.addVizinhoSeguinte(viz, pesoValidoAleatorio(gerador)))
        );
    }

    private double pesoValidoAleatorio(final Random r) {
        return (r.nextDouble() + 0.1) % 1;
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
}
