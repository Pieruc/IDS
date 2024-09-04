package ids.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Contenuto")
public class Contenuto {
    @Column(name="Latitude")
    private double latitude;
    @Column(name="Longitude")
    private double longitude;
    @Id
    @Column(name="Title")
    private String title;
    @Column(name="Description")
    private String description;
    @Column(name="ImageUrl")
    private String imageUrl;
    @JsonIgnore
    @OneToMany(mappedBy = "luogo")
    private Set<Segnalazione> segnalazioni = new HashSet<>();

    public Contenuto() {

    }

    public Contenuto(double latitude, double longitude, String title, String description, String imageUrl) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Segnalazione> getSegnalazioni() {
        return segnalazioni;
    }

    public void addSegnalazione(Segnalazione segnalazione) {
        this.segnalazioni.add(segnalazione);
    }

    public void removeSegnalazione(Segnalazione segnalazione){
        this.segnalazioni.remove(segnalazione);
    }

    @Override
    public String toString() {
        return "Contenuto{" +
                ", latitudine='" + latitude + '\'' +
                ", longitudine='" + longitude + '\'' +
                ", titolo='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
