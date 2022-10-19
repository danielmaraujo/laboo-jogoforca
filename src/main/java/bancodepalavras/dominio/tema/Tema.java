package bancodepalavras.dominio.tema;

import dominio.ObjetoDominioImpl;

public class Tema extends ObjetoDominioImpl {
    private String nome;

    private Tema(long id, String nome) {
        super(id);
        this.nome = nome;
    }

    public Tema criar(long id, String nome){
         return new Tema(id, nome);
    }

    public Tema reconstituir(long id, String nome){
         return new Tema(id, nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
