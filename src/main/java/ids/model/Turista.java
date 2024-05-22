package ids.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Turista")
public class Turista implements Utente, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="Nome")
    private String nome;
    @Column(name="Email")
    private String email;
    @Column(name="Password")
    private String password;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Turista{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void creaItinerario() {

        List<Luogo> itinerario = new ArrayList<Luogo>();

    }

    public boolean segnalaContenuto() {

        String m = "Messaggio di Segnalazione";

        Segnalazione s = new Segnalazione(this, m, false);

        return s.getStato();
    }

    public void modificaRuolo() {

    }

    @Override
    public void crea() {
        System.out.println("Turista creato!");
    }
}
