package generico;

import generico.dto.Input;
import generico.neuronios.Neuronio;

public class Sinapse {
    private final Neuronio ini;
    private final Neuronio fim;
    private double peso;
    private double deltaPeso;
    private Input ultimoInputPassado;

    public Sinapse(final Neuronio ini, final Neuronio fim, final double peso) {
        this.ini = ini;
        this.fim = fim;
        this.peso = peso;
        this.deltaPeso = 0;
        this.ultimoInputPassado = null;
    }

    public Neuronio getIni() {
        return ini;
    }

    public Neuronio getFim() {
        return fim;
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
        this.ultimoInputPassado = new Input(fim, peso, valor);
        return ultimoInputPassado;
    }

    public double getPeso() {
        return peso;
    }
}
