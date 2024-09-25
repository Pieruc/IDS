package ids.Model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Contest {

    @Id
    private String titolo;
    private boolean esclusivita;
    private String descrizione;
    @ManyToMany
    @JoinTable(
            name="contest_turisti",
            joinColumns = @JoinColumn(name="turisti_contest"),
            inverseJoinColumns = @JoinColumn(name="turisti_partecipanti")
    )
    private List<Turista> tPartecipanti= new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name="contest_contributor",
            joinColumns = @JoinColumn(name="contributor_contest"),
            inverseJoinColumns = @JoinColumn(name = "contributor_partecipanti")
    )
    private List<Contributor> cPartecipanti = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name="contest_turista_autorizzati",
            joinColumns = @JoinColumn(name="turista_autorizzati_contest"),
            inverseJoinColumns = @JoinColumn(name = "turista_autorizzati_partecipanti")
    )
    private List<TuristaAutorizzato> tAPartecipanti;

    @ManyToMany
    @JoinTable(
            name="contest_contributor_autorizzati",
            joinColumns = @JoinColumn(name="contributor_autorizzati_contest"),
            inverseJoinColumns = @JoinColumn(name = "contributor_autorizzati_partecipanti")
    )
    private List<ContributorAutorizzato> cAPartecipanti = new ArrayList<>();


    public Contest() {

    }

    public Contest(String titolo, boolean esclusivita, String descrizione) {

        this.titolo = titolo;
        this.esclusivita = esclusivita;
        this.descrizione = descrizione;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public boolean getEsclusivita() {
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

    public List<Turista> gettPartecipanti(){
        return tPartecipanti;
    }
    public List<Contributor> getcPartecipanti(){
        return cPartecipanti;
    }

    public List<TuristaAutorizzato> gettAPartecipanti(){
        return tAPartecipanti;
    }
    public List<ContributorAutorizzato> getcAPartecipanti(){
        return cAPartecipanti;
    }

    public List<Utente> getPartecipanti(){
        List<Utente> partecipanti= new ArrayList<>();
        partecipanti.addAll(this.gettPartecipanti());
        partecipanti.addAll(this.gettAPartecipanti());
        partecipanti.addAll(this.getcPartecipanti());
        partecipanti.addAll(this.getcAPartecipanti());
        return partecipanti;
    }

    public void addTuristaPartecipante(Turista t){
        tPartecipanti.add(t);
        t.getPartecipazioni().add(this);
    }
    public void removeTuristaPartecipante(Turista t){
        tPartecipanti.remove(t);
        t.getPartecipazioni().remove(this);
    }

    public void addContributorPartecipante(Contributor c){
        cPartecipanti.add(c);
        c.getPartecipazioni().add(this);
    }

    public void removeContributorPartecipante(Contributor c){
        cPartecipanti.remove(c);
        c.getPartecipazioni().remove(this);
    }

    public void addTuristaAutorizzatoPartecipante(TuristaAutorizzato tA){
        tAPartecipanti.add(tA);
        tA.getPartecipazioni().add(this);
    }

    public void removeTuristaAutorizzatoPartecipante(TuristaAutorizzato tA){
        tAPartecipanti.remove(tA);
        tA.getPartecipazioni().remove(this);
    }

    public void addContributorAutorizzatoPartecipante(ContributorAutorizzato cA){
        cAPartecipanti.add(cA);
        cA.getPartecipazioni().add(this);
    }
    public void removeContributorAutorizzatoPartecipante(ContributorAutorizzato cA){
        cAPartecipanti.remove(cA);
        cA.getPartecipazioni().remove(this);
    }
}
