package jogoforca;

import bancodepalavras.dominio.letra.LetraFactory;
import bancodepalavras.dominio.palavra.Palavra;
import bancodepalavras.dominio.palavra.PalavraAppService;
import bancodepalavras.dominio.palavra.PalavraFactory;
import bancodepalavras.dominio.palavra.PalavraFactoryImpl;
import bancodepalavras.dominio.tema.TemaFactory;
import bancodepalavras.dominio.tema.TemaFactoryImpl;
import jogoforca.dominio.boneco.BonecoFactory;
import jogoforca.dominio.jogador.JogadorFactory;
import jogoforca.dominio.jogador.JogadorFactoryImpl;
import jogoforca.dominio.rodada.Rodada;
import jogoforca.dominio.rodada.RodadaAppService;
import jogoforca.dominio.rodada.RodadaFactory;
import jogoforca.dominio.rodada.sorteio.RodadaSorteioFactory;
import jogoforca.embdr.BDRRepositoryFactory;
import jogoforca.emmemoria.MemoriaRepositoryFactory;
import jogoforca.imagem.ElementoGraficoImagemFactory;
import jogoforca.texto.ElementoGraficoTextoFactory;

public class Aplicacao {
    private static final String[] TIPOS_REPOSITORY_FACTORY = {"memoria", "relacional"};
    private static final String[] TIPOS_ELEMENTO_GRAFICO_FACTORY = {"texto", "imagem"};
    private static final String[] TIPOS_RODADA_FACTORY = {"sorteio"};

    private static Aplicacao soleInstance;

    private String tipoRepositoryFactory = TIPOS_REPOSITORY_FACTORY[0];
    private String tipoElementoGraficoFactory = TIPOS_ELEMENTO_GRAFICO_FACTORY[0];
    private String tipoRodadaFactory = TIPOS_RODADA_FACTORY[0];

    private RepositoryFactory repositoryFactory;
    private ElementoGraficoFactory elementoGraficoFactory;
    private RodadaFactory rodadaFactory;

    public static Aplicacao getSoleInstance() {
        if (soleInstance == null){
            soleInstance = new Aplicacao();
        }
        return soleInstance;
    }

    private Aplicacao() {
    }

    public void configurar(){
        if(tipoRepositoryFactory.equals(TIPOS_REPOSITORY_FACTORY[0])) repositoryFactory = MemoriaRepositoryFactory.getSoleInstance();
        if(tipoRepositoryFactory.equals(TIPOS_REPOSITORY_FACTORY[1])) repositoryFactory = BDRRepositoryFactory.getSoleInstance();

        if(tipoElementoGraficoFactory.equals(TIPOS_ELEMENTO_GRAFICO_FACTORY[0])) elementoGraficoFactory = ElementoGraficoTextoFactory.getSoleInstance();
        if(tipoElementoGraficoFactory.equals(TIPOS_ELEMENTO_GRAFICO_FACTORY[1])) elementoGraficoFactory = ElementoGraficoImagemFactory.getSoleInstance();


        if(tipoRodadaFactory.equals(TIPOS_RODADA_FACTORY[0])){
            RodadaSorteioFactory.createSoleInstance(repositoryFactory.getRodadaRepository(), repositoryFactory.getTemaRepository(), repositoryFactory.getPalavraRepository());
            rodadaFactory = RodadaSorteioFactory.getSoleInstance();
        }

        TemaFactoryImpl.createSoleInstance(repositoryFactory.getTemaRepository());
        PalavraFactoryImpl.createSoleInstance(repositoryFactory.getPalavraRepository());
        JogadorFactoryImpl.createSoleInstance(repositoryFactory.getJogadorRepository());

        PalavraAppService.createSoleInstance(repositoryFactory.getPalavraRepository(), repositoryFactory.getTemaRepository(), getPalavraFactory());
        RodadaAppService.createSoleInstance(repositoryFactory.getRodadaRepository(), repositoryFactory.getJogadorRepository(), getRodadaFactory());

        Palavra.setLetraFactory(elementoGraficoFactory);
        Rodada.setBonecoFactory(elementoGraficoFactory);
    }

    public String[] getTiposRepositoryFactory(){
        return TIPOS_REPOSITORY_FACTORY;
    }

    public void setTipoRepositoryFactory(String tipo) {
        this.tipoRepositoryFactory = tipoRepositoryFactory;
    }

    public RepositoryFactory getRepositoryFactory() {
        return repositoryFactory;
    }

    public String[] getTiposElementoGraficoFactory(){
        return TIPOS_ELEMENTO_GRAFICO_FACTORY;
    }

    public void setTipoElementoGraficoFactory(String tipo) {
        this.tipoElementoGraficoFactory = tipoElementoGraficoFactory;
    }

    private ElementoGraficoFactory getElementoGraficoFactory() {
        return elementoGraficoFactory;
    }

    public BonecoFactory getBonecoFactory() {
        return getElementoGraficoFactory();
    }

    public LetraFactory getLetraFactory() {
        return getElementoGraficoFactory();
    }

    public String[] getTiposRodadaFactory(){
        return TIPOS_RODADA_FACTORY;
    }

    public void setTipoRodadaFactory(String tipoRodadaFactory) {
        this.tipoRodadaFactory = tipoRodadaFactory;
    }

    public RodadaFactory getRodadaFactory() {
        return rodadaFactory;
    }

    public TemaFactory getTemaFactory() {
        return TemaFactoryImpl.getSoleInstance();
    }

    public PalavraFactory getPalavraFactory() {
        return PalavraFactoryImpl.getSoleInstance();
    }

    public JogadorFactory getJogadorFactory(){
        return JogadorFactoryImpl.getSoleInstance();
    }
}
