package ids.model;

public class Contenuto {

    private Contributor autore;
    private Object contenuto;
    private Luogo luogo;

    public Contenuto() {

    }

    public Contenuto(Contributor autore, Object contenuto, Luogo luogo) {

        this.autore = autore;
        this.contenuto = contenuto;
        this.luogo = luogo;

    }

    public Contributor getAutore() {
        return autore;
    }

    public void setAutore(Contributor autore) {
        this.autore = autore;
    }

    public Object getContenuto() {
        return contenuto;
    }

    public void setContenuto(Object contenuto) {
        this.contenuto = contenuto;
    }

    public Luogo getLuogo() {
        return luogo;
    }

    public void setLuogo(Luogo luogo) {
        this.luogo = luogo;
    }
}
