import generico.CamadaNeural;
import generico.RedeNeural;
import percepton.Perceptron;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        final var rede = new RedeNeural(2);
        final var camada = new CamadaNeural(List.of(new Perceptron()));
        rede.addCamada(camada);

        System.out.println(rede.computarEntrada(List.of(0.0, 0.0)));
        System.out.println(rede.computarEntrada(List.of(1.0, 0.0)));
        System.out.println(rede.computarEntrada(List.of(0.0, 1.0)));
        System.out.println(rede.computarEntrada(List.of(1.0, 1.0)));
    }
}
