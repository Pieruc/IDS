package ids.model;
import java.util.List;

public class Itinerario {

    private String nome;
    private List<Luogo> percorso;

    public Itinerario(){

    }

    public Itinerario(String nome, List<Luogo> percorso) {

        this.nome = nome;
        this.percorso = percorso;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Luogo> getPercorso() {
        return percorso;
    }

    public void setPercorso(List<Luogo> percorso) {
        this.percorso = percorso;
    }

    public void addLuogo(Luogo luogo) {
        this.percorso.add(luogo);
    }

    public void removeLuogo(Luogo luogo) {
        this.percorso.remove(luogo);
    }

}
