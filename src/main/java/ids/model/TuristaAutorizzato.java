package ids.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Turista Autorizzato")
public class TuristaAutorizzato implements Utente{

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

    @Override
    public String toString() {
        return "Turista Autorizzato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void archivia(){

    }
    @Override
    public void crea(){
        System.out.println("Turista autorizzato creato!");
    }

}
