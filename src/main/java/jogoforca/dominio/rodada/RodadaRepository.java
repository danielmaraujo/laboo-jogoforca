package jogoforca.dominio.rodada;

import jogoforca.dominio.jogador.Jogador;
import repository.Repository;
import repository.RepositoryException;

import java.util.List;

public interface RodadaRepository extends Repository {
    public Rodada getPorId(long id);
    public List<Rodada> getPorJogador(Jogador jogador);

    public void inserir(Rodada rodada) throws RepositoryException;
    public void atualizar(Rodada rodada) throws RepositoryException;
    public void remover(Rodada rodada) throws RepositoryException;
}
