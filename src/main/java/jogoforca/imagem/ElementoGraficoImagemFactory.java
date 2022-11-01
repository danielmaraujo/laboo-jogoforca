package jogoforca.imagem;

import bancodepalavras.dominio.letra.Letra;
import bancodepalavras.dominio.letra.LetraFactory;
import bancodepalavras.dominio.letra.imagem.LetraImagemFactory;
import jogoforca.ElementoGraficoFactory;
import jogoforca.dominio.boneco.Boneco;
import jogoforca.dominio.boneco.BonecoFactory;
import jogoforca.dominio.boneco.imagem.BonecoImagemFactory;

public class ElementoGraficoImagemFactory implements ElementoGraficoFactory {
    BonecoFactory bonecoFactory;
    LetraFactory letraFactory;
    private static ElementoGraficoImagemFactory soleInstance;

    public static ElementoGraficoImagemFactory getSoleInstance(){
        if(soleInstance==null){
            soleInstance = new ElementoGraficoImagemFactory();
        }
        return soleInstance;
    }

    private ElementoGraficoImagemFactory() {
        letraFactory = LetraImagemFactory.getSoleInstance();
        bonecoFactory = BonecoImagemFactory.getSoleInstance();
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
