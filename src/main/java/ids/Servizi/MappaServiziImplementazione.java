package ids.Servizi;

import ids.Model.*;
import ids.Repository.ItinerarioRepository;
import ids.Repository.ContenutoRepository;
import ids.Repository.SegnalazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MappaServiziImplementazione implements MappaServizi {

    @Autowired
    ContenutoRepository cRep;

    @Autowired
    ItinerarioRepository iRep;

    @Autowired
    SegnalazioneRepository sRep;

    @Override
    public void save(Contenuto m) {
        cRep.save(m);
    }

    @Override
    public List<Contenuto> getAllContenuti() {
        return cRep.findAll();
    }

    @Override
    public boolean aggiungiContenuto(double lat, double lon, String title, String description, String imageUrl) {
        try {
            Contenuto contenuto = new Contenuto(lat, lon, title, description, imageUrl);
            cRep.save(contenuto);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Contenuto trovaContenutoConTitolo(String titolo) {
        if (cRep.findById(titolo).isPresent()) {
            return cRep.findById(titolo).get();
        } else {
            return null;
        }
    }

    @Override
    public void eliminaContenuto(Contenuto m) {
        m.getSegnalazioni().removeAll(m.getSegnalazioni());
        for (Segnalazione s : sRep.findAll()) {
            if (s.getLuogo().equals(m)) {
                sRep.delete(s);
            }
        }
        for (Itinerario i : iRep.findAll()) {
            if (i.getLuogo().contains(m)) {
                i.removeLuogo(m);
            }
        }
        cRep.delete(m);
    }
}
