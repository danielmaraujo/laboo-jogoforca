package bancodepalavras.dominio.tema.emmemoria;

import bancodepalavras.dominio.tema.Tema;
import bancodepalavras.dominio.tema.TemaRepository;
import repository.BDEmMemoria;
import repository.RepositoryException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MemoriaTemaRepository implements TemaRepository {
    private static MemoriaTemaRepository soleInstance;

    public static MemoriaTemaRepository getSoleInstance(){
        if(soleInstance==null){
            soleInstance = new MemoriaTemaRepository();
        }
        return soleInstance;
    }

    public MemoriaTemaRepository() {
    }

    @Override
    public Tema getPorId(long id) {
        return BDEmMemoria.BDTema.get(id);
    }

    @Override
    public List<Tema> getTodas() {
        return new ArrayList<>(BDEmMemoria.BDTema.values());
    }

    @Override
    public List<Tema> getPorNome(String nome) {
        return BDEmMemoria.BDTema.values().stream().filter(tema -> tema.getNome().equals(nome)).collect(Collectors.toList());
    }

    @Override
    public void inserir(Tema tema) throws RepositoryException {
        BDEmMemoria.BDTema.put(tema.getId(), tema);
    }

    @Override
    public void atualizar(Tema tema) throws RepositoryException {
        BDEmMemoria.BDTema.put(tema.getId(), tema);
    }

    @Override
    public void remover(Tema tema) throws RepositoryException {

        BDEmMemoria.BDTema.remove(tema.getId());
    }

    @Override
    public long getProximoId() {
        if (BDEmMemoria.BDTema.isEmpty()){
            return 1;
        }

        return Collections.max(BDEmMemoria.BDTema.keySet()) + 1;
    }
}
