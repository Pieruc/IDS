package ids.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Luogo {

    @Id
    @Column(name="Nome")
    private String nome;
    @Column(name="Latitudine")
    private double latitudine;
    @Column(name="Longitudine")
    private double longitudine;
    @JsonIgnore
    @OneToMany(mappedBy = "luogo")
    private Set<Segnalazione> segnalazioni = new HashSet<>();


    public Luogo () {}

    public Luogo(String nome, double latitudine, double longitudine) {

        this.nome = nome;
        this.latitudine = latitudine;
        this.longitudine = longitudine;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    public Set<Segnalazione> getSegnalazioni() {
        return segnalazioni;
    }

    public void setSegnalazioni(Set<Segnalazione> segnalazioni) {
        this.segnalazioni = segnalazioni;
    }
}
