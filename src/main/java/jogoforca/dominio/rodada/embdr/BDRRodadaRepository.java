package jogoforca.dominio.rodada.embdr;

import jogoforca.dominio.jogador.Jogador;
import jogoforca.dominio.rodada.Rodada;
import jogoforca.dominio.rodada.RodadaRepository;
import jogoforca.dominio.rodada.emmemoria.MemoriaRodadaRepository;
import repository.RepositoryException;

import java.util.List;

public class BDRRodadaRepository implements RodadaRepository {
    private static BDRRodadaRepository soleInstance;

    public static BDRRodadaRepository getSoleInstance(){
        if(soleInstance==null){
            soleInstance = new BDRRodadaRepository();
        }
        return soleInstance;
    }

    private BDRRodadaRepository() {
    }

    @Override
    public Rodada getPorId(long id) {
        return null;
    }

    @Override
    public List<Rodada> getPorJogador(Jogador jogador) {
        return null;
    }

    @Override
    public void inserir(Rodada rodada) throws RepositoryException {

    }

    @Override
    public void atualizar(Rodada rodada) throws RepositoryException {

    }

    @Override
    public void remover(Rodada rodada) throws RepositoryException {

    }

    @Override
    public long getProximoId() {
        return 0;
    }
}
