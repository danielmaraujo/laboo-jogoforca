package jogoforca.dominio.rodada;

import bancodepalavras.dominio.letra.Letra;
import bancodepalavras.dominio.palavra.Palavra;
import bancodepalavras.dominio.tema.Tema;
import dominio.ObjetoDominioImpl;
import jogoforca.dominio.boneco.Boneco;
import jogoforca.dominio.boneco.BonecoFactory;
import jogoforca.dominio.jogador.Jogador;

import java.util.*;
import java.util.stream.Collectors;

public class Rodada extends ObjetoDominioImpl {
    private static int maxPalavras = 3;
    private static int maxErros = 10;
    private static int pontosQuandoDescobreTodasAsPalavras = 100;
    private static int pontosPorLetraEncoberta = 15;
    private Jogador jogador;
    private Boneco boneco;
    private static BonecoFactory bonecoFactory;
    private Item[] itens;
    private Letra[] letrasErradas;

    public static int getMaxPalavras() {
        return maxPalavras;
    }

    public static void setMaxPalavras(int maxPalavras) {
        Rodada.maxPalavras = maxPalavras;
    }

    public static int getMaxErros() {
        return maxErros;
    }

    public static void setMaxErros(int maxErros) {
        Rodada.maxErros = maxErros;
    }

    public static int getPontosQuandoDescobreTodasAsPalavras() {
        return pontosQuandoDescobreTodasAsPalavras;
    }

    public static void setPontosQuandoDescobreTodasAsPalavras(int pontosQuandoDescobreTodasAsPalavras) {
        Rodada.pontosQuandoDescobreTodasAsPalavras = pontosQuandoDescobreTodasAsPalavras;
    }

    public static int getPontosPorLetraEncoberta() {
        return pontosPorLetraEncoberta;
    }

    public static void setPontosPorLetraEncoberta(int pontosPorLetraEncoberta) {
        Rodada.pontosPorLetraEncoberta = pontosPorLetraEncoberta;
    }

    public static BonecoFactory getBonecoFactory() {
        return bonecoFactory;
    }

    public static void setBonecoFactory(BonecoFactory bonecoFactory) {
        Rodada.bonecoFactory = bonecoFactory;
    }

    public static Rodada criar(long id, Palavra[] palavras, Jogador jogador){
        return new Rodada(id, palavras, jogador);
    }

    private Rodada(long id, Palavra[] palavras, Jogador jogador) {
        super(id);
        if (palavras.length > maxErros){
            throw new IllegalArgumentException("Quantidade de palavras acima do limite");
        }

        if(bonecoFactory != null) {
            Item[] auxitens = new Item[palavras.length];

            for (int i = 0; i < palavras.length; i++) {
                if(palavras[i].getTema().equals(palavras[0].getTema())){
                    auxitens[i] = Item.criar(new Random().nextLong(), palavras[i]);
                }else{
                    throw new IllegalArgumentException("Todos os temas de palavras precisam ser iguais");
                }
            }

            this.itens = auxitens;
            this.jogador = jogador;
            this.boneco = bonecoFactory.getBoneco();
            this.letrasErradas = new Letra[this.maxErros];
        }
        else{
            throw new IllegalStateException("BonecoFactory precisa ser setado para instanciar Rodada");
        }
    }

    private Rodada(long id, Jogador jogador, Boneco boneco, Item[] itens, Letra[] letrasErradas) {
        super(id);
        if(bonecoFactory != null) {
            this.jogador = jogador;
            this.boneco = boneco;
            this.itens = itens;
            this.letrasErradas = letrasErradas;
        }else{
            throw new IllegalStateException("BonecoFactory precisa ser setado para instanciar Rodada");
        }
    }

    public Jogador getJogador() {
        return jogador;
    }

    public Tema getTema() {
        return itens[0].getPalavra().getTema();
    }

    public Palavra[] getPalavras(){
        Palavra[] palavras = new Palavra[itens.length];

        for (int i = 0; i < itens.length; i++) {
            palavras[i] = itens[i].getPalavra();
        }

        return palavras;
    }

    public int getNumPalavras(){
        return itens.length;
    }

    public void tentar(char codigo){
        if(!this.encerrou()) {
            boolean acertou = false;
            for (Item item : itens) {
                if (item.tentar(codigo)) acertou = true;
            }

            if (!acertou) {
                for (int i = 0; i < letrasErradas.length; i++) {
                    if (letrasErradas[i] == null){
                        letrasErradas[i] = Palavra.getLetraFactory().getLetra(codigo);
                        break;
                    }
                }
            }
        }
    }

    public void arriscar(String[] palavras){
        if(!this.encerrou()){
            for (int i = 0; i < itens.length; i++) {
                itens[i].arriscar(palavras[i]);
            }
        }
    }

    public void exibirItens(Object contexto){
        for (Item item : itens) {
            item.exibir(contexto);
            System.out.println();
        }
    }

    public void exibirBoneco(Object contexto){
        boneco.exibir(contexto, this.getQtdeErros());
    }

    public void exibirLetrasErradas(Object contexto){
        for (Letra letra : letrasErradas) {
            if (letra != null) letra.exibir(contexto);
        }
    }

    public Letra[] getErradas(){
        List<Letra> letrasErradasLista = Arrays.stream(letrasErradas).filter(Objects::nonNull).collect(Collectors.toList());
        return letrasErradasLista.toArray(new Letra[letrasErradasLista.size()]);
    }

    public Letra[] getCertas(){
        Set<Letra> certas = new HashSet<>();

        for (Item item : itens) {
            for (Letra letraDescoberta : item.getLetrasDescobertas()) {
                certas.add(letraDescoberta);
            }
        }

        return certas.toArray(new Letra[certas.size()]);
    }

    public Letra[] getTentativas(){
        List<Letra> tentativas = new ArrayList<>();

        tentativas.addAll(Arrays.stream(this.getErradas()).filter(Objects::nonNull).collect(Collectors.toList()));
        tentativas.addAll(Arrays.stream(this.getCertas()).filter(Objects::nonNull).collect(Collectors.toList()));

        return tentativas.toArray(new Letra[tentativas.size()]);
    }

    public int calcularPontos(){
        int pontos = 0;

        if (this.descobriu()) {
            pontos = 100;
        }

        for (Item item : itens){
            pontos += item.getLetrasDescobertas().length * 15;
        }

        return pontos;
    }

    public boolean encerrou(){
        if(this.arriscou() || this.descobriu() || this.getErradas().length >= maxErros){
            return true;
        }else{
            return false;
        }
    }

    public boolean descobriu(){
        boolean descobriu = true;

        for (Item item : itens) {
            if (!item.descobriu()){
                descobriu = false;
            }
        }

        return descobriu;
    }

    public boolean arriscou(){
        for (Item item : itens) {
            if (item.arriscou()){
                return true;
            }
        }
        return false;
    }

    public int getQtdeTentativasRestantes(){
        return maxErros - getQtdeErros();
    }

    public int getQtdeErros(){
        return this.getErradas().length;
    }

    public int getQtdeAcertos() {
        return this.getCertas().length;
    }

    public int getQtdeTentativas() {
        return this.getTentativas().length;
    }
}
