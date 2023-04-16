package generico;

import java.util.LinkedList;
import java.util.List;

public class RedeNeural {
    private final List<CamadaNeural> camadas;
    private final CamadaNeural camadaSensorial;
    private CamadaNeural ultimaCamadaDiscreta;

    public RedeNeural(final int qtdNeuroniosEntrada) {
        this.camadas = new LinkedList<>();

        final List<Neuronio> neuroniosSensoriais = new LinkedList<>();
        for(int i = 0; i < qtdNeuroniosEntrada; i++) {
            neuroniosSensoriais.add(new NeuronioSensorial());
        }

        this.camadaSensorial = new CamadaNeural(neuroniosSensoriais);
        this.ultimaCamadaDiscreta = null;
        this.camadas.add(camadaSensorial);
    }

    public void addCamada(final CamadaNeural camada) {
        this.camadas.add(camada);
        this.ultimaCamadaDiscreta = camada;
    }

    public List<Double> computarEntrada(final List<Double> entrada) {
        if(ultimaCamadaDiscreta == null)
            throw new RuntimeException("Sem camadas discretas");

        this.camadaSensorial.addEntrada(entrada);
        this.camadas.forEach(CamadaNeural::ativarNeuronios);

        return ultimaCamadaDiscreta.getOutput();
    }
}
