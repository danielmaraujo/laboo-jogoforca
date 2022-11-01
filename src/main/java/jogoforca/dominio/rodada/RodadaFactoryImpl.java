package jogoforca.dominio.rodada;

import bancodepalavras.dominio.palavra.PalavraRepository;
import bancodepalavras.dominio.tema.TemaRepository;
import factory.EntityFactory;

public abstract class RodadaFactoryImpl extends EntityFactory implements RodadaFactory  {
    TemaRepository temaRepository;
    PalavraRepository palavraRepository;

    protected RodadaFactoryImpl(RodadaRepository repository, TemaRepository temaRepository, PalavraRepository palavraRepository) {
        super(repository);
        this.temaRepository = temaRepository;
        this.palavraRepository = palavraRepository;
    }

    protected RodadaRepository getRodadaRepository(){
        return (RodadaRepository) this.getRepository();
    }

    protected TemaRepository getTemaRepository() {
        return temaRepository;
    }

    protected PalavraRepository getPalavraRepository() {
        return palavraRepository;
    }
}
