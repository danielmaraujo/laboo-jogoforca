import bancodepalavras.dominio.letra.Letra;
import bancodepalavras.dominio.palavra.PalavraAppService;
import bancodepalavras.dominio.tema.TemaFactory;
import bancodepalavras.dominio.tema.TemaRepository;
import jogoforca.Aplicacao;
import jogoforca.dominio.jogador.Jogador;
import jogoforca.dominio.rodada.Rodada;
import jogoforca.dominio.rodada.RodadaAppService;
import repository.RepositoryException;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Aplicacao aplicacao = Aplicacao.getSoleInstance();

    public static void main(String[] args) {
        aplicacao.configurar();

        PalavraAppService palavraAppService = PalavraAppService.getSoleInstance();

        TemaRepository temaRepository = aplicacao.getRepositoryFactory().getTemaRepository();
        TemaFactory temaFactory = aplicacao.getTemaFactory();

        try {
            temaRepository.inserir(temaFactory.getTema("Carro"));
            temaRepository.inserir(temaFactory.getTema("Nome"));
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }

        palavraAppService.novaPalavra("fusca", 1);
        palavraAppService.novaPalavra("palio", 1);
        palavraAppService.novaPalavra("corsa", 1);
        palavraAppService.novaPalavra("felipe", 2);
        palavraAppService.novaPalavra("ana", 2);
        palavraAppService.novaPalavra("jorge", 2);

        System.out.println("Digite seu nome: ");
        String nomeJogador = scanner.next();

        Jogador jogador = aplicacao.getJogadorFactory().getJogador(nomeJogador);
        try {
            aplicacao.getRepositoryFactory().getJogadorRepository().inserir(jogador);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }

        jogarRodada(jogador);
    }

    private static void jogarRodada(Jogador jogador) {
        RodadaAppService rodadaAppService = RodadaAppService.getSoleInstance();

        Rodada rodada = rodadaAppService.novaRodada(jogador);
        System.out.println("Tema das palavras: " + rodada.getTema());

        do {
            System.out.println("Tentativas restantes: " + rodada.getQtdeTentativasRestantes());
            System.out.println("Tentativas anteriores: ");
            for (Letra tentativa : rodada.getTentativas()) {
                tentativa.exibir(null);
                System.out.print(" ");
            }
            System.out.println();

            System.out.println("Palavras:");
            rodada.exibirItens(null);
            System.out.println();
            System.out.println("Corpo: ");
            rodada.exibirBoneco(null);
            System.out.println();


            System.out.println("(1) Tentar letra");
            System.out.println("(2) Arriscar");
            String escolha = scanner.next();
            switch (escolha){
                case "1":
                    System.out.print("Digite a letra: ");
                    rodada.tentar(scanner.next().charAt(0));
                    break;
                case "2":
                    String[] palavrasArriscadas = new String[rodada.getNumPalavras()];
                    for (int i = 0; i < palavrasArriscadas.length; i++) {
                        System.out.print("Chute a palavra " + (i + 1)  + " :");
                        palavrasArriscadas[i] = scanner.next();
                    }
                    rodada.arriscar(palavrasArriscadas);
                    break;
            }

            if (rodada.descobriu()) System.out.println("Descobriu");


        }while (!rodada.encerrou());
    }
}
