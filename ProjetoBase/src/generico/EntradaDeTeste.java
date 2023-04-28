package generico;

import java.util.List;

public class EntradaDeTeste {
    private final List<Double> amostra;
    private final List<Double> esperado;

    public EntradaDeTeste(final List<Double> amostra, final List<Double> esperado) {
        if (amostra.size() != esperado.size())
            throw new RuntimeException("Tamanhos invalidos");

        this.amostra = amostra;
        this.esperado = esperado;
    }

    public List<Double> getAmostra() {
        return amostra;
    }

    public List<Double> getEsperado() {
        return esperado;
    }
}
