package jogoforca.dominio.rodada;

import jogoforca.dominio.jogador.Jogador;
import jogoforca.dominio.jogador.JogadorRepository;
import repository.RepositoryException;

public class RodadaAppService {
    RodadaRepository rodadaRepository;
    JogadorRepository jogadorRepository;
    RodadaFactory rodadaFactory;

    private static RodadaAppService soleInstance;

    public static void createSoleInstance(RodadaRepository rodadaRepository, JogadorRepository jogadorRepository, RodadaFactory rodadaFactory){
        soleInstance = new RodadaAppService(rodadaRepository, jogadorRepository, rodadaFactory);
    }

    public static RodadaAppService getSoleInstance(){
        if (soleInstance == null){
            throw new IllegalStateException("Objeto RodadaAppService precisa criado");
        }
        return soleInstance;
    }

    private RodadaAppService(RodadaRepository rodadaRepository, JogadorRepository jogadorRepository, RodadaFactory rodadaFactory) {
        this.rodadaRepository = rodadaRepository;
        this.jogadorRepository = jogadorRepository;
        this.rodadaFactory = rodadaFactory;
    }

    public Rodada novaRodada(long idJogador){
        if (jogadorRepository.getPorId(idJogador) == null){
            throw new IllegalArgumentException("Jogador não encontrado no repositório");
        }

        return rodadaFactory.getRodada(jogadorRepository.getPorId(idJogador));
    }

    public Rodada novaRodada(String nomeJogador) throws JogadorNaoEncontradoException {
        if (jogadorRepository.getPorNome(nomeJogador) == null){
            throw new JogadorNaoEncontradoException(nomeJogador);
        }

        return rodadaFactory.getRodada(jogadorRepository.getPorNome(nomeJogador));
    }

    public Rodada novaRodada(Jogador jogador){
        return rodadaFactory.getRodada(jogador);
    }

    public boolean salvarRodada(Rodada rodada){
        try {
            rodadaRepository.inserir(rodada);
            return true;
        } catch (RepositoryException e) {
            return false;
        }
    }
}

class JogadorNaoEncontradoException extends Exception{
    private String jogador;

    public JogadorNaoEncontradoException(String jogador) {
        this.jogador = jogador;
    }

    public String getJogador() {
        return jogador;
    }
}
