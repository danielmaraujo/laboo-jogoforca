package jogoforca.dominio.boneco.texto;

import jogoforca.dominio.boneco.Boneco;

public class BonecoTexto implements Boneco {
    private static BonecoTexto soleInstance;

    private BonecoTexto() {
    }

    public static BonecoTexto getSoleInstance() {
        if (soleInstance == null) return new BonecoTexto();
        else return soleInstance;
    }

    @Override
    public void exibir(Object contexto, int partes) {
        switch(partes){
            case 1:
                System.out.print("cabeça");
            case 2:
                System.out.print("cabeça, olho esquerdo");
            case 3:
                System.out.print("cabeça, olho esquerdo, olho direito");
            case 4:
                System.out.print("cabeça, olho esquerdo, olho direito, nariz");
            case 5:
                System.out.print("cabeça, olho esquerdo, olho direito, nariz, boca");
            case 6:
                System.out.print("cabeça, olho esquerdo, olho direito, nariz, boca, tronco");
            case 7:
                System.out.print("cabeça, olho esquerdo, olho direito, nariz, boca, tronco, braço esquerdo");
            case 8:
                System.out.print("cabeça, olho esquerdo, olho direito, nariz, boca, tronco, braço esquerdo, braço direito");
            case 9:
                System.out.print("cabeça, olho esquerdo, olho direito, nariz, boca, tronco, braço esquerdo, braço direito, perna esquerda");
            case 10:
                System.out.print("cabeça, olho esquerdo, olho direito, nariz, boca, tronco, braço esquerdo, braço direito, perna esquerda, perna direita");
        }
    }
}
