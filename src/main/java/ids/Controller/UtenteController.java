package ids.controller;

import ids.Servizi.UserServiziImplementazione;
import ids.model.Contributor;
import ids.model.Turista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UtenteController {

    @Autowired
    private UserServiziImplementazione sRep;

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
}
