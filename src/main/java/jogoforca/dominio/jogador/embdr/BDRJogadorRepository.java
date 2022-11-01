package jogoforca.dominio.jogador.embdr;

import jogoforca.dominio.jogador.Jogador;
import jogoforca.dominio.jogador.JogadorRepository;
import repository.RepositoryException;

public class BDRJogadorRepository implements JogadorRepository {
    private static BDRJogadorRepository soleInstance;

    public static BDRJogadorRepository getSoleInstance(){
        if(soleInstance==null){
            soleInstance = new BDRJogadorRepository();
        }
        return soleInstance;
    }

    private BDRJogadorRepository() {
    }

    @Override
    public Jogador getPorId(long id) {
        return null;
    }

    @Override
    public Jogador getPorNome(String nome) {
        return null;
    }

    @Override
    public void inserir(Jogador jogador) throws RepositoryException {

    }

    @Override
    public void atualizar(Jogador jogador) throws RepositoryException {

    }

    @Override
    public void remover(Jogador jogador) throws RepositoryException {

    }

    @Override
    public long getProximoId() {
        return 0;
    }
}
