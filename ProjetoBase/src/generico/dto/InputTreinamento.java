package generico.dto;

import java.util.List;

public class InputTreinamento {
    final List<Double> input;
    final List<Double> expect;

    // TODO: fazer um construtor que recebe uma linha do arquivo
    public InputTreinamento(List<Double> input, List<Double> expect) {
        this.input = input;
        this.expect = expect;
    }

    public List<Double> getInput() {
        return input;
    }

    public List<Double> getExpect() {
        return expect;
    }
}
