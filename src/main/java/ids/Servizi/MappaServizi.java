package ids.Servizi;

import ids.Model.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MappaServizi {
    void save(Contenuto m);
    List<Contenuto> getAllContenuti();
    boolean aggiungiContenuto(double lat, double lon, String title, String description, String imageUrl);
    Contenuto trovaContenutoConTitolo(String titolo);
    void eliminaContenuto(Contenuto m);
}
