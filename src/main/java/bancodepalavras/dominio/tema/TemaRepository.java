package bancodepalavras.dominio.tema;

import repository.Repository;
import repository.RepositoryException;

import java.util.List;

public interface TemaRepository extends Repository {
    public Tema getPorId(long id);
    public List<Tema> getTodas();
    public List<Tema> getPorNome(String nome);

    public void inserir(Tema tema) throws RepositoryException;
    public void atualizar(Tema tema) throws RepositoryException;
    public void remover(Tema tema) throws RepositoryException;
}
