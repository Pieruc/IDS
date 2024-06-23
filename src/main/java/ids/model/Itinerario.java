package ids.Model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Itinerario {

    @Id
    @Column(name="Nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="Luoghi")
    private Set<Luogo> percorso = new HashSet<>();
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="proprietario", referencedColumnName = "Email")
    private Turista proprietario;

    public Itinerario(){

    }

    public Itinerario(String nome, Turista turista){
        this.nome=nome;
        proprietario = turista;
    }

    public String getNome() {
        return nome;
    }

    public Set<Luogo> getPercorso() {
        return percorso;
    }

    public Turista getProprietario() {
        return proprietario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void addLuogo(Luogo luogo) {
        this.percorso.add(luogo);
    }

    public void removeLuogo(Luogo luogo) {
        this.percorso.remove(luogo);
    }

}
