package ids.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Marker")
public class Marker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;
    @Column(name="Latitude")
    private double latitude;
    @Column(name="Longitude")
    private double longitude;
    @Column(name="Title")
    private String title;
    @Column(name="Description")
    private String description;
    @Column(name="ImageUrl")
    private String imageUrl;

    public Marker() {

    }

    public Marker(double latitude, double longitude, String title, String description, String imageUrl) {
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

    @Override
    public String toString() {
        return "Marker{" +
                "id=" + id +
                ", latitudine='" + latitude + '\'' +
                ", longitudine='" + longitude + '\'' +
                ", titolo='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }
}
