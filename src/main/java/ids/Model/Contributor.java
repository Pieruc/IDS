package ids.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Contributor")
public class Contributor implements Utente, Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name="Nome")
    private String nome;
    @Id
    @Column(name="Email")
    private String email;
    @Column(name="Password")
    private String password;
    @JsonIgnore
    @ManyToMany(mappedBy = "cPartecipanti")
    private Set<Contest> partecipazioni=new HashSet<>();
    public Contributor(){

    }
    public Contributor(String n, String e,String p){
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

    @Override
    public String toString() {
        return "Contributor{" +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    public Set<Contest> getPartecipazioni(){
        return partecipazioni;
    }

    public void partecipaContest(Contest contest){
        partecipazioni.add(contest);
        contest.getcPartecipanti().add(this);
    }

    public void fineContest(Contest contest){
        partecipazioni.remove(contest);
        contest.getcPartecipanti().remove(this);
    }

    public void modificaRuolo() {

    }

    @Override
    public void crea() {
        System.out.println("Contributor creato!");
    }
}
