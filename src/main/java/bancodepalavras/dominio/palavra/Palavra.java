package bancodepalavras.dominio.palavra;

import bancodepalavras.dominio.letra.Letra;
import bancodepalavras.dominio.letra.LetraFactory;
import bancodepalavras.dominio.tema.Tema;
import dominio.ObjetoDominioImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Palavra extends ObjetoDominioImpl {
    private Tema tema;
    private static LetraFactory letraFactory;
    private Letra letraEncoberta;
    private Letra[] letras;

    public static LetraFactory getLetraFactory() {
        return letraFactory;
    }

    public static void setLetraFactory(LetraFactory letraFactory) {
        Palavra.letraFactory = letraFactory;
    }

    public static Palavra criar(long id, String palavra, Tema tema){
        return new Palavra(id, palavra, tema);
    }

    public static Palavra reconstituir(long id, String palavra, Tema tema){
        return new Palavra(id, palavra, tema);
    }

    public Palavra(long id, String palavra, Tema tema) {
        super(id);
        if (letraFactory != null){
            this.tema = tema;
            this.letraEncoberta = letraFactory.getLetraEncoberta();

            letras = new Letra[palavra.length()];
            for (int i = 0; i < palavra.length(); i++) {
                letras[i] = letraFactory.getLetra(palavra.charAt(i)) ;
            }

        }else{
            throw new IllegalStateException("LetraFactory precisa ser setado para instanciar Palavra");
        }
    }

    public Letra[] getLetras() {
        return letras;
    }

    public Letra getLetra(int posicao){
        return letras[posicao];
    }

    public void exibir(Object contexto){
        for (Letra letra : letras) {
            letra.exibir(contexto);
        }
    }

    public void exibir(Object contexto, boolean[] posicoes){
        for (int i = 0; i < posicoes.length; i++) {
            if(posicoes[i]){
                letras[i].exibir(contexto);
            }else{
                letraEncoberta.exibir(contexto);
            }
        }
    }

    public int[] tentar(char codigo){
        List<Integer> listaPosicoes = new ArrayList<>();

        for (int i = 0; i < letras.length; i++) {
            if (letras[i].getCodigo() == codigo){
                listaPosicoes.add(i);
            }
        }

        return listaPosicoes.stream().mapToInt(i->i).toArray();
    }

    public Tema getTema() {
        return tema;
    }

    public boolean comparar(String palavra){
        Letra[] vetorLetrasPalavra = new Letra[palavra.length()];
        for (int i = 0; i < palavra.length(); i++) {
            vetorLetrasPalavra[i] = letraFactory.getLetra(palavra.charAt(i)) ;
        }

        return Arrays.equals(Arrays.stream(letras).toArray(), Arrays.stream(vetorLetrasPalavra).toArray());
    }

    public int getTamanho(){
        return letras.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Letra letra : letras) {
            sb.append(letra.getCodigo());
        }

        return sb.toString();
    }
}
