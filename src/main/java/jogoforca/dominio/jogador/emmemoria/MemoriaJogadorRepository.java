package jogoforca.dominio.jogador.emmemoria;

import jogoforca.dominio.jogador.Jogador;
import jogoforca.dominio.jogador.JogadorRepository;
import repository.BDEmMemoria;
import repository.RepositoryException;
import java.util.Collections;

public class MemoriaJogadorRepository implements JogadorRepository {
    private static MemoriaJogadorRepository soleInstance;

    public static MemoriaJogadorRepository getSoleInstance(){
        if(soleInstance==null){
            soleInstance = new MemoriaJogadorRepository();
        }
        return soleInstance;
    }

    private MemoriaJogadorRepository() {
    }

    @Override
    public Jogador getPorId(long id) {
        return BDEmMemoria.BDJogador.get(id);
    }

    @Override
    public Jogador getPorNome(String nome) {
        for (Jogador jogador : BDEmMemoria.BDJogador.values()) {
            if (jogador.getNome().equals("nome")){
                return jogador;
            }
        }

        return null;
    }

    @Override
    public void inserir(Jogador jogador) throws RepositoryException {
        BDEmMemoria.BDJogador.put(jogador.getId(), jogador);
    }

    @Override
    public void atualizar(Jogador jogador) throws RepositoryException {
        BDEmMemoria.BDJogador.put(jogador.getId(), jogador);
    }

    @Override
    public void remover(Jogador jogador) throws RepositoryException {
        BDEmMemoria.BDJogador.remove(jogador.getId());
    }

    @Override
    public long getProximoId() {
        if (BDEmMemoria.BDJogador.isEmpty()){
            return 1;
        }
        return Collections.max(BDEmMemoria.BDJogador.keySet()) + 1;
    }
}
