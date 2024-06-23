package ids.Servizi;

import ids.Model.Itinerario;
import ids.Model.Luogo;
import ids.Model.Marker;
import ids.Repository.ItinerarioRepository;
import ids.Repository.LuogoRepository;
import ids.Repository.MarkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MarkerServiziImplementazione implements MarkerServizi {

    @Autowired
    MarkerRepository mRep;

    @Autowired
    ItinerarioRepository iRep;

    @Autowired
    LuogoRepository lRep;

    @Override
    public void save(Marker m) {
        mRep.save(m);
    }

    @Override
    public ResponseEntity<List<Marker>> listaMarker() {
        try{
            List<Marker> list = new ArrayList<>(mRep.findAll());
            if(list.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Marker> ricercaMarkerConId(int id) {
        Optional<Marker> m = mRep.findById(id);
        return m.map(
                        marker -> new ResponseEntity<>(marker, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public boolean addMarker(double lat, double lon, String title, String description, String imageUrl) {
        try{
            Marker marker = new Marker(lat, lon, title, description, imageUrl);
            mRep.save(marker);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    @Override
    public Marker trovaMarkerConId(int id){
        if(mRep.findById(id).isPresent()){
            return mRep.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public ResponseEntity<Marker> aggiornaMarkerConId(int id, double lat, double lon, String title, String description, String imageUrl) {
        Optional<Marker> marker = mRep.findById(id);
        if(marker.isPresent()){
            Marker temp =  marker.get();
            temp.setLatitude(lat);
            temp.setLongitude(lon);
            temp.setTitle(title);
            temp.setDescription(description);
            temp.setImageUrl(imageUrl);
            Marker mod = mRep.save(temp);
            return new ResponseEntity<>(mod,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public void eliminaMarker(Marker m) {
        mRep.delete(m);
    }

    @Override
    public ResponseEntity<List<Luogo>> listaLuoghi() {
        try {
            List<Luogo> luoghi = new ArrayList<>(lRep.findAll());
            if(luoghi.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(luoghi,HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Itinerario>> listaItinerari() {
        try{
            List<Itinerario> lista = new ArrayList<>(iRep.findAll());
            if(lista.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Itinerario addLuogo(Luogo luogo, String nome) {
        Itinerario it = iRep.findById(nome).orElseThrow(() -> new RuntimeException("Itinerario non trovato"));
        it.addLuogo(luogo);
        return iRep.save(it);
    }

    @Override
    public Luogo creaLuogo(String nome, double latitudine, double longitudine) {
        Luogo nuovo = new Luogo(nome,latitudine,longitudine);
        return lRep.save(nuovo);
    }
}
