package ids.Model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Turista Autorizzato")
public class TuristaAutorizzato implements Utente, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="nome")
    private String nome;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;

    public TuristaAutorizzato() {

    }

    public TuristaAutorizzato(String nome, String email, String password) {
        this.nome = nome;
        this.email = email;
        this.password = password;
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
        return "Turista Autorizzato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void creaItinerario() {

    }

    /*public boolean segnalaContenuto() {

        Turista copia = new Turista(this.nome, this.email, this.password);

        String m = "Messaggio di Segnalazione";

        Segnalazione s = new Segnalazione(copia, m, true);

        return true;
    }*/

    public void modificaRuolo() {

    }

    public void archivia(){

    }

    @Override
    public void crea(){
        System.out.println("Turista autorizzato creato!");
    }

}
