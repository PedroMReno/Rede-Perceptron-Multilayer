package generico.dto;

import java.util.List;

public class Amostra {
    final List<Double> input;
    final List<Double> expect;

    public Amostra(List<Double> input, List<Double> expect) {
        this.input = input;
        this.expect = expect;
    }

    public List<Double> getInput() {
        return input;
    }

    public List<Double> getExpected() {
        return expect;
    }
}
