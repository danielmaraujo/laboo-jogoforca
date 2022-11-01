package jogoforca.dominio.rodada;

import factory.EntityFactory;
import jogoforca.dominio.jogador.Jogador;

public interface RodadaFactory{
    public Rodada getRodada(Jogador jogador);
}
