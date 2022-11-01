package jogoforca.dominio.rodada.sorteio;

import bancodepalavras.dominio.palavra.Palavra;
import bancodepalavras.dominio.palavra.PalavraRepository;
import bancodepalavras.dominio.tema.Tema;
import bancodepalavras.dominio.tema.TemaRepository;
import jogoforca.dominio.jogador.Jogador;
import jogoforca.dominio.rodada.Rodada;
import jogoforca.dominio.rodada.RodadaFactoryImpl;
import jogoforca.dominio.rodada.RodadaRepository;

import java.util.List;
import java.util.Random;

public class RodadaSorteioFactory extends RodadaFactoryImpl {
    private static RodadaSorteioFactory soleInstance;

    public static void createSoleInstance(RodadaRepository repository, TemaRepository temaRepository, PalavraRepository palavraRepository){
        soleInstance = new RodadaSorteioFactory(repository, temaRepository, palavraRepository);
    }

    public static RodadaSorteioFactory getSoleInstance(){
        if (soleInstance == null){
            throw new IllegalStateException("Objeto RodadaSorteioFactory precisa ser criado");
        }
        return soleInstance;
    }

    private RodadaSorteioFactory(RodadaRepository repository, TemaRepository temaRepository, PalavraRepository palavraRepository) {
        super(repository, temaRepository, palavraRepository);
    }

    @Override
    public Rodada getRodada(Jogador jogador) {
        List<Tema> listaTemas = getTemaRepository().getTodas();
        Tema temaSorteado = listaTemas.get(new Random().nextInt(listaTemas.size()));

        List<Palavra> listaPalavrasPorTema = getPalavraRepository().getPorTema(temaSorteado);
        Palavra[] palavrasSorteadas = new Palavra[Rodada.getMaxPalavras()];

        for (int i = 0; i < palavrasSorteadas.length; i++) {
            palavrasSorteadas[i] = listaPalavrasPorTema.get(new Random().nextInt(listaPalavrasPorTema.size()));
        }

        return Rodada.criar(this.getProximoId(), palavrasSorteadas, jogador);
    }
}
