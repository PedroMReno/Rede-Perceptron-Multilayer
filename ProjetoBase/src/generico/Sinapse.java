package generico;

import generico.dto.Input;

public class Sinapse {
    private final Neuronio neuronio;
    private double peso;
    private double deltaPeso;
    private Input ultimoInputPassado;

    public Sinapse(final Neuronio neuronio, final double peso) {
        this.neuronio = neuronio;
        this.peso = peso;
        this.deltaPeso = 0;
        this.ultimoInputPassado = null;
    }

    public Neuronio getNeuronio() {
        return neuronio;
    }

    public Input getUltimoInputPassado() {
        return ultimoInputPassado;
    }

    public void armazenarCorrecao(final double deltaPeso) {
        this.deltaPeso = deltaPeso;
    }

    public void aplicarCorrecao() {
        this.peso += this.deltaPeso;
        this.deltaPeso = 0;
    }

    public Input gerarInput(final double valor) {
        this.ultimoInputPassado = new Input(neuronio, peso, valor);
        return ultimoInputPassado;
    }
}
