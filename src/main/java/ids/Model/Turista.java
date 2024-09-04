package ids.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
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
    private List<Itinerario> itinerari;
    @JsonIgnore
    @OneToMany(mappedBy = "autore")
    private List<Segnalazione> segnalazioni;
    @JsonIgnore
    @ManyToMany(mappedBy = "tPartecipanti")
    private List<Contest> partecipazioni;

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

    public List<Itinerario> getItinerari() {
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

    public List<Contest> getPartecipazioni(){
        return partecipazioni;
    }

    @Override
    public void crea() {
        System.out.println("Turista creato!");
    }

    public void addSegnalazione(Segnalazione s){
        this.segnalazioni.add(s);
    }

    public void removeSegnalazione(Segnalazione s){
        this.segnalazioni.remove(s);
    }
}
