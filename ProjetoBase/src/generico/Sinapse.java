package generico;

public class Sinapse {
    private final Neuronio neuronio;
    private double peso;
    private double deltaPeso;

    public Sinapse(final Neuronio neuronio, final double peso) {
        this.neuronio = neuronio;
        this.peso = peso;
        this.deltaPeso = 0;
    }

    public Neuronio getNeuronio() {
        return neuronio;
    }

    public void armazenarCorrecao(final double deltaPeso) {
        this.deltaPeso = deltaPeso;
    }

    public void aplicarCorrecao() {
        this.peso += this.deltaPeso;
        this.deltaPeso = 0;
    }

    public Input gerarInput(final double valor) {
        return new Input(neuronio, peso, valor);
    }
}
