package ids.model;
import java.util.List;

public class Contest {

    private String titolo;
    private boolean esclusivita;
    private String descrizione;
    private List<Utente> partecipanti;
    private Object contenuto;

    public Contest() {

    }

    public Contest(String titolo, boolean esclusivita, String descrizione, List<Utente> partecipanti, Object contenuto) {

        this.titolo = titolo;
        this.esclusivita = esclusivita;
        this.descrizione = descrizione;
        this.partecipanti = partecipanti;
        this.contenuto = contenuto;

    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public boolean isEsclusivita() {
        return esclusivita;
    }

    public void setEsclusivita(boolean esclusivita) {
        this.esclusivita = esclusivita;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Utente> getPartecipanti() {
        return partecipanti;
    }

    public void setPartecipanti(List<Utente> partecipanti) {
        this.partecipanti = partecipanti;
    }

    public Object getContenuto() {
        return contenuto;
    }

    public void setContenuto(Object contenuto) {
        this.contenuto = contenuto;
    }

}
