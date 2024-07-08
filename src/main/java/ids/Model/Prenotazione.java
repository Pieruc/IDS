package ids.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private double importo;
    @JoinColumn
    @ManyToMany
    private List<Luogo> luoghi;


    public long getId() {
        return id;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public List<Luogo> getLuoghi() {
        return luoghi;
    }

    public void setLuoghi(List<Luogo> luoghi) {
        this.luoghi = luoghi;
    }
}
