package ids.Model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Segnalazioni")
public class Segnalazione {

    @Id
    @Column(name = "Titolo")
    private String titolo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="Autore", nullable = false)
    private Turista autore;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="Luogo", nullable = false)
    private Luogo luogo;
    @Column(name="Messaggio", nullable = false)
    private String messaggio;
    @Column(name="stato")
    private boolean stato;

    public Segnalazione(){

    }

    public Segnalazione(String titolo, Turista turista, Luogo luogo, String messaggio){
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

    public Luogo getLuogo() {
        return luogo;
    }

    public void setLuogo(Luogo luogo) {
        this.luogo = luogo;
    }
}
