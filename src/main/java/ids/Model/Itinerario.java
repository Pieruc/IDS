package ids.Model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Itinerario {

    @Id
    @Column(name="Nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="Luoghi")
    private List<Contenuto> percorso;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="proprietario", referencedColumnName = "Email")
    private Turista proprietario;

    public Itinerario(){

    }

    public Itinerario(String nome, Turista turista, List<Contenuto> luoghi){
        this.nome=nome;
        proprietario = turista;
        percorso = luoghi;
    }

    public String getNome() {
        return nome;
    }

    public List<Contenuto> getPercorso() {
        return percorso;
    }

    public Turista getProprietario() {
        return proprietario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Contenuto> getLuogo(){
        return this.percorso;
    }

    public void addLuogo(Contenuto luogo) {
        this.percorso.add(luogo);
    }

    public void removeLuogo(Contenuto luogo) {
        this.percorso.remove(luogo);
    }

}
