package repository;

import bancodepalavras.dominio.palavra.Palavra;
import bancodepalavras.dominio.tema.Tema;
import jogoforca.dominio.jogador.Jogador;
import jogoforca.dominio.rodada.Rodada;

import java.util.HashMap;
import java.util.Map;

public class BDEmMemoria {
    public static Map<Long, Palavra> BDPalavra = new HashMap<>();
    public static Map<Long, Tema> BDTema = new HashMap<>();
    public static Map<Long, Rodada> BDRodada = new HashMap<>();
    public static Map<Long, Jogador> BDJogador = new HashMap<>();
}
