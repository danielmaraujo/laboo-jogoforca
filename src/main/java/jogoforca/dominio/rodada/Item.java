package jogoforca.dominio.rodada;

import bancodepalavras.dominio.letra.Letra;
import bancodepalavras.dominio.palavra.Palavra;
import dominio.ObjetoDominioImpl;

import java.util.ArrayList;
import java.util.List;

public class Item extends ObjetoDominioImpl {
    private Palavra palavra;
    private boolean[] posicoesDescobertas;
    private String palavraArriscada = null;

    static Item criar(long id, Palavra palavra){
        return new Item(id, palavra);
    }

    public static Item reconstituir(long id, Palavra palavra, boolean[] posicoesDescobertas, String palavraArriscada){
        return new Item(id,palavra, posicoesDescobertas, palavraArriscada);
    }

    public Item(long id, Palavra palavra) {
        super(id);
        this.palavra = palavra;
        this.posicoesDescobertas = new boolean[palavra.getTamanho()];
        for (int i = 0; i < posicoesDescobertas.length; i++) {
            posicoesDescobertas[i] = false;
        }
    }

    public Item(long id, Palavra palavra, boolean[] posicoesDescobertas, String palavraArriscada) {
        super(id);
        this.palavra = palavra;
        this.posicoesDescobertas = posicoesDescobertas;
        this.palavraArriscada = palavraArriscada;
    }

    public Palavra getPalavra() {
        return palavra;
    }

    public Letra[] getLetrasDescobertas() {
        List<Letra> listaLetras = new ArrayList<>();

        for (int i = 0; i < posicoesDescobertas.length; i++) {
            if(posicoesDescobertas[i]){
                listaLetras.add(palavra.getLetra(i));
            }
        }

        return listaLetras.toArray(new Letra[listaLetras.size()]);
    }

    public Letra[] getLetrasEncobertas() {
        List<Letra> listaLetras = new ArrayList<>();

        for (int i = 0; i < posicoesDescobertas.length; i++) {
            if(!posicoesDescobertas[i]){
                listaLetras.add(palavra.getLetra(i));
            }
        }

        return listaLetras.toArray(new Letra[listaLetras.size()]);
    }

    public int qtdeLetrasEncobertas(){
        int contador = 0;

        for (boolean posicao : posicoesDescobertas) {
            if (!posicao){
                contador++;
            }
        }

        return contador;
    }

    public int calcularPontosLetrasEncobertas(int valorPorLetraEncoberta){
        return this.qtdeLetrasEncobertas() * valorPorLetraEncoberta;
    }

    public boolean descobriu(){
        return qtdeLetrasEncobertas() == 0;
    }

    public void exibir(Object contexto){
        palavra.exibir(contexto, posicoesDescobertas);
    }

    boolean tentar(char codigo){
        if (palavra.tentar(codigo).length == 0) return false;

        for (int posicao : palavra.tentar(codigo)) {
            posicoesDescobertas[posicao] = true;
        }
        return true;
    }

    void arriscar(String palavra){
        palavraArriscada = palavra;
    }

    public String getPalavraArriscada() {
        return palavraArriscada;
    }

    public boolean arriscou(){
        return palavraArriscada != null;
    }

    public boolean acertou(){
        return palavraArriscada.equals(palavra.toString());
    }
}
