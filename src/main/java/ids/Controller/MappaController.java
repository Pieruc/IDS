package ids.Controller;

import ids.Model.*;
import ids.Servizi.MappaServiziImplementazione;
import ids.Model.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mappa")
public class MappaController {

    @Autowired
    private MappaServiziImplementazione mRep;

    @GetMapping("/listaMarker")
    public ResponseEntity<List<Marker>> listaMarker(){
        return  mRep.listaMarker();
    }

    @GetMapping("/ricercaMarkerConID/{id}")
    public ResponseEntity<Marker> ricercaMarkerConId(@PathVariable int id){
        return mRep.ricercaMarkerConId(id);
    }

    @PostMapping("/addMarker")
    public ResponseEntity<String> addMarker(@RequestParam double lat, @RequestParam double lon, @RequestParam String title,
                                            @RequestParam String description, @RequestParam String imageUrl) {
        boolean reusult = mRep.addMarker(lat, lon, title, description, imageUrl);
        if(reusult){
            return new ResponseEntity<>("Aggiunto!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Impossibile aggiungere", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/aggiornaMarkerConId")
    public ResponseEntity<Marker> aggiornaMarkerConId(@RequestParam int id, @RequestParam double lat, @RequestParam double lon, @RequestParam String title,
                                                      @RequestParam String description, @RequestParam String imageUrl){
        return mRep.aggiornaMarkerConId(id, lat, lon, title, description, imageUrl);
    }
    @DeleteMapping("/eliminaMarker/{id}")
    public ResponseEntity<HttpStatus> eliminaMarker(@PathVariable int id){
        mRep.eliminaMarker(mRep.trovaMarkerConId(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/creaLuogo")
    public ResponseEntity<Luogo> creaLuogo(@RequestBody Luogo luogo){
        Luogo creato = mRep.creaLuogo(luogo.getNome(),luogo.getLatitudine(),luogo.getLongitudine());
        return new ResponseEntity<>(creato,HttpStatus.OK);
    }

    @GetMapping("/listaLuoghi")
    public ResponseEntity<List<Luogo>> listaLuoghi(){
        return mRep.listaLuoghi();
    }
}
