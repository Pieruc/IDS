package ids.Model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "Curatore")
public class Curatore implements Utente, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Column(name="nome")
    private String nome;
    @Id
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

    @Override
    public String toString() {
        return "Curatore{" +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public void crea() {
        System.out.println("Curatore creato!");
    }
}
