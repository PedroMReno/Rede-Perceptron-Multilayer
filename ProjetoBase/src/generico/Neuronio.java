package generico;

import java.util.LinkedList;
import java.util.List;

public abstract class Neuronio {
    protected List<Sinapse> sinapsesSeguintes;
    protected List<Sinapse> sinapsesAnteriores;
    protected Double ultimoValorCalculado;
    protected List<Input> inputBuffer;

    public Neuronio() {
        this.ultimoValorCalculado = null;
        this.inputBuffer = new LinkedList<>();
        this.sinapsesSeguintes = new LinkedList<>();
    }

    public double getUltimoValorCalculado() {
        return this.ultimoValorCalculado;
    }

    public void addVizinhoSeguinte(final Neuronio vizinho, final double peso) {
        this.sinapsesSeguintes.add(new Sinapse(vizinho, peso));
        vizinho.addVizinhoAnterior(this, peso);
    }

    public void addVizinhoAnterior(final Neuronio vizinho, final double peso) {
        this.sinapsesAnteriores.add(new Sinapse(vizinho, peso));
    }

    public void addInput(final Input input) {
        this.inputBuffer.add(input);
    }

    protected abstract double computarEntradas();

    protected abstract double efetuarCalculo(final double entradaComputada);

    protected void propagarCalculo(final double resultado) {
        for(Sinapse s : sinapsesSeguintes) {
            final var input = s.gerarInput(resultado);
            s.getNeuronio().addInput(input);
        }
    }

    public double ativarNeuronio() {
        final var resultado = efetuarCalculo(computarEntradas());
        this.ultimoValorCalculado = resultado;

        propagarCalculo(resultado);
        this.inputBuffer.clear();

        return resultado;
    }
}
