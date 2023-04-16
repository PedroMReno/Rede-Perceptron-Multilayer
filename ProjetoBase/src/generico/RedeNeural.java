package generico;

import java.util.LinkedList;
import java.util.List;

public class RedeNeural {
    private final List<CamadaNeural> camadas;
    private final CamadaNeural camadaSensorial;
    private CamadaNeural ultimaCamada;

    public RedeNeural(final int qtdNeuroniosEntrada) {
        this.camadas = new LinkedList<>();

        final List<Neuronio> neuroniosSensoriais = new LinkedList<>();
        for(int i = 0; i < qtdNeuroniosEntrada; i++) {
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

    public List<Double> computarEntrada(final List<Double> entrada) {
        if(ultimaCamada == camadaSensorial)
            throw new RuntimeException("Sem camadas discretas");

        this.camadaSensorial.addEntrada(entrada);
        this.camadas.forEach(CamadaNeural::ativarNeuronios);

        return ultimaCamada.getOutput();
    }
}
