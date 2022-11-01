package jogoforca.dominio.jogador;

import bancodepalavras.dominio.tema.Tema;
import jogoforca.dominio.rodada.Rodada;
import repository.Repository;
import repository.RepositoryException;

import java.util.List;

public interface JogadorRepository extends Repository {
    public Jogador getPorId(long id);
    public Jogador getPorNome(String nome);

    public void inserir(Jogador jogador) throws RepositoryException;
    public void atualizar(Jogador jogador) throws RepositoryException;
    public void remover(Jogador jogador) throws RepositoryException;
}
