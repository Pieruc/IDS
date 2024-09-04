package ids.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Segnalazioni")
public class Segnalazione {

    @Id
    @Column(name = "Titolo")
    private String titolo;
    @ManyToOne
    @JoinColumn(name="Autore", nullable = false)
    private Turista autore;
    @ManyToOne
    @JoinColumn(name="Luogo", nullable = false)
    private Contenuto luogo;
    @Column(name="Messaggio")
    private String messaggio;
    @Column(name="Stato")
    private boolean stato;

    public Segnalazione(){

    }

    public Segnalazione(String titolo, Turista turista, Contenuto luogo, String messaggio){
        this.titolo = titolo;
        this.autore = turista;
        this.luogo = luogo;
        this.messaggio = messaggio;
        this.stato = false;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Turista getAutore() {
        return autore;
    }

    public void setAutore(Turista autore) {
        this.autore = autore;
    }

    public Contenuto getLuogo() {
        return luogo;
    }

    public void setLuogo(Contenuto luogo) {
        this.luogo = luogo;
    }

}
