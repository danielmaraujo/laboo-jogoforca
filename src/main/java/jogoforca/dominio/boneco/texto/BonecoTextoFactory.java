package jogoforca.dominio.boneco.texto;

import jogoforca.dominio.boneco.Boneco;
import jogoforca.dominio.boneco.BonecoFactory;
import jogoforca.dominio.rodada.emmemoria.MemoriaRodadaRepository;

public class BonecoTextoFactory implements BonecoFactory {
    private static BonecoTextoFactory soleInstance;

    public static BonecoTextoFactory getSoleInstance(){
        if(soleInstance==null){
            soleInstance = new BonecoTextoFactory();
        }
        return soleInstance;
    }

    private BonecoTextoFactory() {
    }

    @Override
    public Boneco getBoneco() {
        return BonecoTexto.getSoleInstance();
    }
}
