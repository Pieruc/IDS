package ids.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Curatore")
public class Curatore implements Utente, Serializable {
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

    public Curatore() {

    }

    public Curatore(String nome, String email, String password) {
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

    @Override
    public void crea() {
        System.out.println("Curatore creato!");
    }
}
