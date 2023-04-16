package generico;

public class Sinapse {
    private final Neuronio neuronio;
    private final double peso;

    public Sinapse(final Neuronio neuronio, final double peso) {
        this.neuronio = neuronio;
        this.peso = peso;
    }

    public Neuronio getNeuronio() {
        return neuronio;
    }

    public double getPeso() {
        return peso;
    }

    public Input gerarInput(final double valor) {
        return new Input(neuronio, peso, valor);
    }
}
