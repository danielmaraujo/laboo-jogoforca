package jogoforca.dominio.rodada.emmemoria;

import jogoforca.dominio.jogador.Jogador;
import jogoforca.dominio.rodada.Rodada;
import jogoforca.dominio.rodada.RodadaRepository;
import repository.BDEmMemoria;
import repository.RepositoryException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MemoriaRodadaRepository implements RodadaRepository {
    private static MemoriaRodadaRepository soleInstance;

    public static MemoriaRodadaRepository getSoleInstance(){
        if(soleInstance==null){
            soleInstance = new MemoriaRodadaRepository();
        }
        return soleInstance;
    }

    private MemoriaRodadaRepository() {
    }

    @Override
    public Rodada getPorId(long id) {
        return BDEmMemoria.BDRodada.get(id);
    }

    @Override
    public List<Rodada> getPorJogador(Jogador jogador) {
        return BDEmMemoria.BDRodada.values().stream().filter(rodada -> rodada.getJogador().equals(jogador)).collect(Collectors.toList());
    }

    @Override
    public void inserir(Rodada rodada) throws RepositoryException {
        BDEmMemoria.BDRodada.put(rodada.getId(), rodada);
    }

    @Override
    public void atualizar(Rodada rodada) throws RepositoryException {
        BDEmMemoria.BDRodada.put(rodada.getId(), rodada);
    }

    @Override
    public void remover(Rodada rodada) throws RepositoryException {
        BDEmMemoria.BDRodada.remove(rodada.getId());
    }

    @Override
    public long getProximoId() {
        if (BDEmMemoria.BDRodada.isEmpty()){
            return 1;
        }
        return Collections.max(BDEmMemoria.BDRodada.keySet()) + 1;
    }
}
