package ids.Model;

import jakarta.persistence.*;

import java.io.Serializable;

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

    public boolean caricaContenuto() {

        Object content = new Object();
        Luogo luogo = new Luogo();

        Contenuto contenuto = new Contenuto(this, content, luogo);

        return false;
    }

    public void modificaRuolo() {

    }

    @Override
    public void crea() {
        System.out.println("Contributor creato!");
    }
}
