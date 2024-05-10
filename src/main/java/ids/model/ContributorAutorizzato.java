package ids.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Contributor Autorizzato")
public class ContributorAutorizzato extends Contributor{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="nome")
    private String nome;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;

    public ContributorAutorizzato() {

    }

    public ContributorAutorizzato(String nome, String email, String password) {
        this.nome = nome;
        this.email = email;
        this.password = password;
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

}
