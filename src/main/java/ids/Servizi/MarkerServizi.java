package ids.Servizi;

import ids.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MarkerServizi {
    void save(Marker m);
    ResponseEntity<List<Marker>> listaMarker();
    ResponseEntity<Marker> ricercaMarkerConId(@PathVariable int id);
    boolean addMarker(@RequestParam double lat, @RequestParam double lon, @RequestParam String title,
                      @RequestParam String description, @RequestParam String imageUrl);
    Marker trovaMarkerConId(@RequestParam int id);
    ResponseEntity<Marker> aggiornaMarkerConId(@PathVariable int id, @RequestParam double lat, @RequestParam double lon,
                                               @RequestParam String title, @RequestParam String description,
                                               @RequestParam String imageUrl);
    void eliminaMarker(Marker m);
}
