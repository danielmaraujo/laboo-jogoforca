package jogoforca.dominio.boneco.imagem;

import jogoforca.dominio.boneco.Boneco;

public class BonecoImagem implements Boneco {
    private static BonecoImagem soleInstance;

    private BonecoImagem() {
    }

    public static BonecoImagem getSoleInstance() {
        if (soleInstance == null) return new BonecoImagem();
        else return soleInstance;
    }

    @Override
    public void exibir(Object contexto, int partes) {
    }
}
