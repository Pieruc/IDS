package ids.Model;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Set<Turista> tPartecipanti = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name="contest_contributor",
            joinColumns = @JoinColumn(name="contributor_contest"),
            inverseJoinColumns = @JoinColumn(name = "contributor_partecipanti")
    )
    private Set<Contributor> cPartecipanti = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name="contest_turista_autorizzati",
            joinColumns = @JoinColumn(name="turista_autorizzati_contest"),
            inverseJoinColumns = @JoinColumn(name = "turista_autorizzati_partecipanti")
    )
    private Set<TuristaAutorizzato> tAPartecipanti = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name="contest_contributor_autorizzati",
            joinColumns = @JoinColumn(name="contributor_autorizzati_contest"),
            inverseJoinColumns = @JoinColumn(name = "contributor_autorizzati_partecipanti")
    )
    private Set<ContributorAutorizzato> cAPartecipanti = new HashSet<>();

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

    public Set<Turista> gettPartecipanti(){
        return tPartecipanti;
    }
    public Set<Contributor> getcPartecipanti(){
        return cPartecipanti;
    }

    public Set<TuristaAutorizzato> gettAPartecipanti(){
        return tAPartecipanti;
    }
    public Set<ContributorAutorizzato> getcAPartecipanti(){
        return cAPartecipanti;
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
