package jogoforca.texto;

import bancodepalavras.dominio.letra.Letra;
import bancodepalavras.dominio.letra.LetraFactory;
import bancodepalavras.dominio.letra.texto.LetraTextoFactory;
import jogoforca.ElementoGraficoFactory;
import jogoforca.dominio.boneco.Boneco;
import jogoforca.dominio.boneco.BonecoFactory;
import jogoforca.dominio.boneco.texto.BonecoTextoFactory;

public class ElementoGraficoTextoFactory implements ElementoGraficoFactory {
    BonecoFactory bonecoFactory;
    LetraFactory letraFactory;
    private static ElementoGraficoTextoFactory soleInstance;

    public static ElementoGraficoTextoFactory getSoleInstance(){
        if(soleInstance==null){
            soleInstance = new ElementoGraficoTextoFactory();
        }
        return soleInstance;
    }

    private ElementoGraficoTextoFactory() {
        letraFactory = LetraTextoFactory.getSoleInstance();
        bonecoFactory = BonecoTextoFactory.getSoleInstance();
    }

    @Override
    public Letra getLetra(char codigo) {
        return letraFactory.getLetra(codigo);
    }

    @Override
    public Letra getLetraEncoberta() {
        return letraFactory.getLetraEncoberta();
    }

    @Override
    public Boneco getBoneco() {
        return bonecoFactory.getBoneco();
    }
}
