package generico;

import java.util.LinkedList;
import java.util.List;

public abstract class Neuronio {
    protected List<Sinapse> sinapsesSeguintes;
    protected List<Sinapse> sinapsesAnteriores;
    protected Double ultimoValorCalculado;
    protected Double ultimoInputFeedforwarding;
    protected Double ultimoInputRetroPropagation;
    protected List<Input> inputBuffer;

    public Neuronio() {
        this.ultimoValorCalculado = null;
        this.ultimoInputFeedforwarding = null;
        this.ultimoInputRetroPropagation = null;
        this.inputBuffer = new LinkedList<>();
        this.sinapsesSeguintes = new LinkedList<>();
        this.sinapsesAnteriores = new LinkedList<>();
    }

    public double getUltimoValorCalculado() {
        return this.ultimoValorCalculado;
    }

    public void addVizinhoSeguinte(final Neuronio vizinho, final double peso) {
        this.sinapsesSeguintes.add(new Sinapse(vizinho, peso));
        vizinho.addVizinhoAnterior(this, peso);
    }

    private void addVizinhoAnterior(final Neuronio vizinho, final double peso) {
        this.sinapsesAnteriores.add(new Sinapse(vizinho, peso));
    }

    public void addInput(final Input input) {
        this.inputBuffer.add(input);
    }

    protected abstract double computarEntradas();

    protected abstract double funcaoDeAtivacao(final double entradaComputada);
    protected abstract double derivadaDaFuncaoDeAtivacao(final double entradaComputada);

    public void feedforward() {
        this.ultimoInputFeedforwarding = computarEntradas();
        final var resultado = funcaoDeAtivacao(this.ultimoInputFeedforwarding);
        this.ultimoValorCalculado = resultado;

        for(Sinapse s : sinapsesSeguintes) {
            final var input = s.gerarInput(resultado);
            s.getNeuronio().addInput(input);
        }

        this.inputBuffer.clear();
    }

    public void backPropagation(final double taxaDeAprendizado) {
        this.ultimoInputRetroPropagation = computarEntradas();
        final var termoDeCorrecao = this.ultimoInputRetroPropagation *
                derivadaDaFuncaoDeAtivacao(this.ultimoInputFeedforwarding);

        for(Sinapse s : sinapsesAnteriores) {
            final var input = s.gerarInput(termoDeCorrecao);
            s.getNeuronio().addInput(input);

            s.armazenarCorrecao(taxaDeAprendizado * termoDeCorrecao * s.getNeuronio().ultimoValorCalculado);
        }

        this.inputBuffer.clear();
    }

    public void ajustarPeso() {

    }
}
