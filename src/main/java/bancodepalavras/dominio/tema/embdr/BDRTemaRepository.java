package bancodepalavras.dominio.tema.embdr;

import bancodepalavras.dominio.tema.Tema;
import bancodepalavras.dominio.tema.TemaRepository;
import bancodepalavras.dominio.tema.emmemoria.MemoriaTemaRepository;
import repository.RepositoryException;

import java.util.List;

public class BDRTemaRepository implements TemaRepository {
    private static BDRTemaRepository soleInstance;

    public static BDRTemaRepository getSoleInstance(){
        if(soleInstance==null){
            soleInstance = new BDRTemaRepository();
        }
        return soleInstance;
    }

    @Override
    public Tema getPorId(long id) {
        return null;
    }

    @Override
    public List<Tema> getTodas() {
        return null;
    }

    @Override
    public List<Tema> getPorNome(String nome) {
        return null;
    }

    @Override
    public void inserir(Tema tema) throws RepositoryException {

    }

    @Override
    public void atualizar(Tema tema) throws RepositoryException {

    }

    @Override
    public void remover(Tema tema) throws RepositoryException {

    }

    @Override
    public long getProximoId() {
        return 0;
    }
}
