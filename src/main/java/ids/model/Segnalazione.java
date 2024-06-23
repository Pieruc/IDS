package ids.Model;

public class Segnalazione {

    private Turista autore;
    private String messaggio;
    private boolean stato;

    public Segnalazione(){

    }

    public Segnalazione(Turista t, String m, boolean s){
        this.autore = new Turista();
        this.messaggio = m;
        this.stato = s;
    }

    public Turista getAutore() {
        return autore;
    }

    public void setAutore(Turista autore) {
        this.autore = autore;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public boolean getStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

}
