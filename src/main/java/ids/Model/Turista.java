package ids.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Turista")
public class Turista implements Utente, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="Email")
    private String email;
    @Column(name="Nome")
    private String nome;
    @Column(name="Password")
    private String password;
    @JsonIgnore
    @OneToMany(mappedBy = "proprietario")
    private Set<Itinerario> itinerari = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "autore")
    private Set<Segnalazione> segnalazioni = new HashSet<>();
    @JsonIgnore
    @ManyToMany(mappedBy = "tPartecipanti")
    private Set<Contest> partecipazioni = new HashSet<>();

    public Turista(){

    }
    public Turista(String n, String e,String p){
        nome=n;
        email=e;
        password=p;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Itinerario> getItinerari() {
        return itinerari;
    }

    @Override
    public String toString() {
        return "Turista{" +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Set<Contest> getPartecipazioni(){
        return partecipazioni;
    }

    @Override
    public void crea() {
        System.out.println("Turista creato!");
    }

    public Set<Segnalazione> getSegnalazioni() {
        return segnalazioni;
    }

    public void setSegnalazioni(Set<Segnalazione> segnalazioni) {
        this.segnalazioni = segnalazioni;
    }

}
