package ids.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Curatore")
public class Curatore extends Contributor{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="nome")
    private String nome;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;

    public Curatore() {

    }

    public Curatore(String nome, String email, String password) {
        this.nome = nome;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Curatore{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public boolean verificaContenuto(){
        return true;
    }

    public void creaCampagnaSocial(){

    }

    public void cancellaPuntoDiInteresse(){

    }

}
