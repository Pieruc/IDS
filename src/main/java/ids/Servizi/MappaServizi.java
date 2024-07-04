package ids.Servizi;

import ids.Model.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MappaServizi {
    void save(Marker m);
    ResponseEntity<List<Marker>> listaMarker();
    ResponseEntity<Marker> ricercaMarkerConId(int id);
    boolean addMarker(double lat, double lon, String title, String description, String imageUrl);
    Marker trovaMarkerConId(int id);
    ResponseEntity<Marker> aggiornaMarkerConId(int id, double lat, double lon, String title, String description, String imageUrl);
    void eliminaMarker(Marker m);
    ResponseEntity<List<Luogo>> listaLuoghi();
    ResponseEntity<List<Itinerario>> listaItinerari();
    Itinerario addLuogo(Luogo luogo, String nome);
    Luogo creaLuogo(String nome, double latitudine, double longitudine);
}
