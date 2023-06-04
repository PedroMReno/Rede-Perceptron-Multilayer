package generico;

import generico.dto.Amostra;
import generico.neuronios.Neuronio;
import generico.neuronios.NeuronioSensorial;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RedeNeural {
    private final List<CamadaNeural> camadas;
    private final CamadaNeural camadaSensorial;
    private CamadaNeural ultimaCamada;

    public RedeNeural(final int qtdNeuroniosSensorial) {
        this.camadas = new LinkedList<>();

        final List<Neuronio> neuroniosSensoriais = new LinkedList<>();
        for (int i = 0; i < qtdNeuroniosSensorial; i++) {
            neuroniosSensoriais.add(new NeuronioSensorial());
        }

        this.camadaSensorial = new CamadaNeural(neuroniosSensoriais);
        this.ultimaCamada = camadaSensorial;
        this.camadas.add(camadaSensorial);
    }

    public void addCamada(final CamadaNeural camada) {
        this.ultimaCamada.ligarTotalmenteCom(camada);

        this.camadas.add(camada);
        this.ultimaCamada = camada;
    }

    public List<Double> predict(final List<Double> entrada) {
        if (ultimaCamada == camadaSensorial)
            throw new RuntimeException("Sem camadas discretas");

        this.camadaSensorial.addEntrada(entrada);
        this.camadas.forEach(CamadaNeural::feedForwarding);

        return ultimaCamada.getOutput();
    }

    public void fit(final List<Amostra> amostras, final double taxaDeAprendizado, final int epocas) {
        if (ultimaCamada == camadaSensorial)
            throw new RuntimeException("Sem camadas discretas");

        for (int i = 0; i < epocas; i++)
            executarEpoca(amostras, taxaDeAprendizado);
    }

    public void fit(final List<Amostra> amostras, final List<Amostra> conjuntoDeValidacao,
                    final double taxaDeAprendizado, final double erroMaximo) {
        if (ultimaCamada == camadaSensorial)
            throw new RuntimeException("Sem camadas discretas");

        for (int i = 0; i < 500; i++) {
            executarEpoca(amostras, taxaDeAprendizado);

            double erroQuadratico = 0.0;
            for(final Amostra validacao : conjuntoDeValidacao) {
                final var result = predict(validacao.getInput());
                final var erros = erroDeFit(validacao.getExpect(), result);

                double erroDaRede = 0.0;
                for(Double e : erros)
                    erroDaRede += Math.pow(e, 2);

                erroQuadratico += erroDaRede / 2;
            }

            erroQuadratico = erroQuadratico / conjuntoDeValidacao.size();

            if(erroQuadratico < erroMaximo)
                break;
        }
    }

    private double executarEpoca(List<Amostra> amostras, double taxaDeAprendizado) {
        double erroDaEpoca = 0;

        for (final Amostra amostra : amostras) {
            // FeedForwarding
            final var result = predict(amostra.getInput());
            final var erros = erroDeFit(amostra.getExpect(), result);

            ultimaCamada.addEntrada(erros);

            // Backpropagation
            final var iteCamadas = this.camadas.listIterator(this.camadas.size());
            while (iteCamadas.hasPrevious())
                iteCamadas.previous().backPropagation(taxaDeAprendizado);

            // Ajuste de pesos
            this.camadas.forEach(CamadaNeural::aplicarDeltaPesos);

            // Calculo de erro
            for (double erro : erros) {
                erroDaEpoca += Math.pow(erro, 2) / 2;
            }
        }

        return erroDaEpoca / amostras.size();
    }

    private List<Double> erroDeFit(final List<Double> expected, final List<Double> resultado) {
        final List<Double> erros = new ArrayList<>();

        final var iteExp = expected.iterator();
        final var iteResul = resultado.iterator();
        while (iteExp.hasNext()) {
            erros.add(iteExp.next() - iteResul.next());
        }

        return erros;
    }
}
