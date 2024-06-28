package ids.Model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Contributor Autorizzato")
public class ContributorAutorizzato implements Utente, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    public ContributorAutorizzato() {

    }

    public ContributorAutorizzato(String nome, String email, String password) {
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
        return "Contributor Autorizzato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public boolean caricaContenuto() {

        Contributor copia = new Contributor(this.nome, this.email, this.password);

        Object content = new Object();
        Luogo luogo = new Luogo();

        Contenuto contenuto = new Contenuto(copia, content, luogo);

        return true;
    }

    public void modificaRuolo() {

    }

    @Override
    public void crea() {
        System.out.println("Contributor autorizzato creato!");
    }
}
