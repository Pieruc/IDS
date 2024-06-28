package ids.Controller;

import ids.Model.*;
import ids.Servizi.MarkerServiziImplementazione;
import ids.Servizi.UtenteServiziImplementazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    private UtenteServiziImplementazione sRep;

    @Autowired
    private MarkerServiziImplementazione mRep;

    @GetMapping("/listaTuristi")
    public ResponseEntity<List<Turista>> listaTuristi(){
        return sRep.listaTuristi();
    }

    @GetMapping("/listaContributor")
    public ResponseEntity<List<Contributor>> listaContributor(){
        return sRep.listaContributor();
    }

    @GetMapping("/ricercaTuristaConMail/{e}")
    public ResponseEntity<Turista> ricercaTuristaConMail(@PathVariable String e){
        return sRep.ricercaTuristaConMail(e);
    }

    @GetMapping("/ricercaContributorConMail/{e}")
    public ResponseEntity<Contributor> ricercaContributorConMail(@PathVariable String e){
        return sRep.ricercaContributorConMail(e);
    }

    @PostMapping("/addUtente")
    public ResponseEntity<String> addUtente(@RequestParam String tipo, @RequestParam String n, @RequestParam String e, @RequestParam String p){
        boolean result = sRep.addUtente(tipo,n,e,p);
        if(result){
            return new ResponseEntity<>("Aggiunto!",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Impossibile aggiungere",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/aggiornaTuristaConMail")
    public ResponseEntity<Turista> aggiornaTuristaConMail(@RequestParam String nome, @RequestParam String email,  @RequestParam String password){
        return sRep.aggiornaTuristaConMail(nome,email,password);
    }

    @PostMapping("/aggiornaContributorConMail")
    public ResponseEntity<Contributor> aggiornaContributorConMail(@RequestParam String nome, @RequestParam String email,  @RequestParam String password){
        return sRep.aggiornaContributorConMail(nome,email,password);
    }

    @DeleteMapping("/eliminaTurista/{e}")
    public ResponseEntity<HttpStatus> eliminaTurista(@PathVariable String e){
        sRep.eliminaTurista(sRep.trovaTuristaConMail(e));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/eliminaContributor/{e}")
    public ResponseEntity<HttpStatus> eliminaContributor(@PathVariable String e){
        sRep.eliminaContributor(sRep.trovaContributorConMail(e));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/creaItinerario/{nome}")
    public void creaItinerario(@RequestBody Turista turista, @PathVariable String nome){
        sRep.creaItinerario(turista,nome);
    }

    @PutMapping("/aggiungiLuogoAdItinerario/{nomeIT}")
    public ResponseEntity<Itinerario> addLuogo(@RequestBody Luogo luogo,@PathVariable String nomeIT){
        Itinerario it = mRep.addLuogo(luogo,nomeIT);
        return new ResponseEntity<>(it,HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String tipo, @RequestParam String nome, @RequestParam String email, @RequestParam String password){
        boolean result = sRep.registerCheck(tipo,nome,email,password);
        if(result){
            return new ResponseEntity<>("Creato con successo!",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Qualcosa non va!",HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/listaItinerari")
    public ResponseEntity<List<Itinerario>> listaItinerari(){
        return mRep.listaItinerari();
    }

    @PostMapping("/addSegnalazione")
    public ResponseEntity<String> addSegnalazione(@RequestBody Segnalazione segnalazione){
        boolean result = sRep.addSegnalazione(segnalazione);
        if(result){
            return new ResponseEntity<>("Aggiunto!",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Impossibile aggiungere",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listaSegnalazioni")
    public ResponseEntity<List<Segnalazione>> listaSegnalazioni(){
        return sRep.listaSegnalazioni();
    }

    @DeleteMapping("eliminaSegnalazione/{e}")
    public ResponseEntity<HttpStatus> eliminaSegnalazione(@PathVariable String e){
        sRep.eliminaSegnalazione(sRep.trovaSegnalazione(e));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/verificaSegnalazione/{e}")
    public ResponseEntity<String> verificaSegnalazione(@PathVariable String e){
        if(sRep.verificaSegnalazione(e)){
            return new ResponseEntity<>("Segnalazione Verificata!", HttpStatus.OK);
        }
        else return new ResponseEntity<>("Segnalazione non verificata!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
