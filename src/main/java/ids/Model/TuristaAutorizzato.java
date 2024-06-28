package ids.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Turista Autorizzato")
public class TuristaAutorizzato implements Utente, Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name="nome")
    private String nome;

    @Id
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @JsonIgnore
    @ManyToMany(mappedBy = "tAPartecipanti")
    private Set<Contest> partecipazioni = new HashSet<>();
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

    @Override
    public String toString() {
        return "Turista Autorizzato{" +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Set<Contest> getPartecipazioni(){
        return partecipazioni;
    }

    @Override
    public void crea(){
        System.out.println("Turista autorizzato creato!");
    }

}
