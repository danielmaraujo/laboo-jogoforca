package bancodepalavras.dominio.tema;

import factory.EntityFactory;

public class TemaFactoryImpl extends EntityFactory implements TemaFactory{
    private static TemaFactoryImpl soleInstance;

    public static void createSoleInstance(TemaRepository repository){
        soleInstance = new TemaFactoryImpl(repository);
    }

    public static TemaFactoryImpl getSoleInstance(){
        if (soleInstance == null){
            throw new IllegalStateException("Objeto TemaFactoryImpl precisa ser criado");
        }
        return soleInstance;
    }

    private TemaFactoryImpl(TemaRepository repository) {
        super(repository);
    }

    private TemaRepository getTemaRepository(){
        return (TemaRepository) this.getRepository();
    }

    @Override
    public Tema getTema(String nome) {
        return Tema.criar(this.getProximoId(), nome);
    }
}
