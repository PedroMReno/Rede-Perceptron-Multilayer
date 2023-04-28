package generico.dto;

import generico.Neuronio;

public class Input {
    private final Neuronio gerador;
    private final double peso;
    private final double valorPuro;

    public Input(final Neuronio gerador, final double peso, final double valorPuro) {
        this.gerador = gerador;
        this.peso = peso;
        this.valorPuro = valorPuro;
    }

    public Input(final double valor) {
        this.gerador = null;
        this.peso = 1;
        this.valorPuro = valor;
    }

    public Neuronio getGerador() {
        return gerador;
    }

    public double getPeso() {
        return peso;
    }

    public double getValorPuro() {
        return valorPuro;
    }
}
