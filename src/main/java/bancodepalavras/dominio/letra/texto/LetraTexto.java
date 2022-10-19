package bancodepalavras.dominio.letra.texto;

import bancodepalavras.dominio.letra.Letra;

public class LetraTexto extends Letra {
    public LetraTexto(char codigo) {
        super(codigo);
    }

    @Override
    public void exibir(Object contexto) {
        System.out.print(this.getCodigo());
    }
}


