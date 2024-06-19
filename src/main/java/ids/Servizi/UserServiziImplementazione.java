package ids.Servizi;

import ids.model.Contributor;
import ids.model.Turista;
import ids.model.Utente;
import ids.model.UtenteFactory;
import ids.repository.ContributorRepository;
import ids.repository.TuristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiziImplementazione implements UtenteServizi {

    @Autowired
    TuristaRepository tRep;

    @Autowired
    ContributorRepository cRep;

    @Override
    public void save(Turista t) {
        tRep.save(t);
    }

    @Override
    public void save(Contributor c) {
        cRep.save(c);
    }

    @Override
    public ResponseEntity<List<Turista>> listaTuristi() {
        try{
            List<Turista> list = new ArrayList<>(tRep.findAll());
            if(list.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Contributor>> listaContributor() {
        try{
            List<Contributor> list = new ArrayList<>(cRep.findAll());
            if(list.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Turista> ricercaTuristaConMail(@PathVariable String e){
        Optional<Turista> t = tRep.findById(e);
        return t.map(
                turista -> new ResponseEntity<>(turista, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Contributor> ricercaContributorConMail(@PathVariable String e){
        Optional<Contributor> c = cRep.findById(e);
        return c.map(
                        contributor -> new ResponseEntity<>(contributor, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public boolean addUtente(@RequestParam String tipo, @RequestParam String n, @RequestParam String e, @RequestParam String p) {
        try {
            UtenteFactory factory = new UtenteFactory();
            Utente u = factory.getUtente(tipo, n, e, p);
            if (u instanceof Turista) {
                tRep.save((Turista) u);
            } else if (u instanceof Contributor) {
                cRep.save((Contributor) u);
            } else {
                throw new Exception();
            }
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    public ResponseEntity<Turista> aggiornaTuristaConMail(String nome, String email, String password) {
        Optional<Turista> vecchio = tRep.findById(email);
        if(vecchio.isPresent()){
            Turista temp = vecchio.get();
            temp.setNome(nome);
            temp.setEmail(email);
            temp.setPassword(password);
            Turista mod = tRep.save(temp);
            return new ResponseEntity<>(mod,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @Override
    public ResponseEntity<Contributor> aggiornaContributorConMail(String nome, String email, String password) {
        Optional<Contributor> vecchio = cRep.findById(email);
        if(vecchio.isPresent()){
            Contributor temp = vecchio.get();
            temp.setNome(nome);
            temp.setEmail(email);
            temp.setPassword(password);
            Contributor mod = cRep.save(temp);
            return new ResponseEntity<>(mod,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public Contributor trovaContributorConMail(@PathVariable String email) {
        if(cRep.findById(email).isPresent()){
            return cRep.findById(email).get();
        }
        else return null;
    }

    public Turista trovaTuristaConMail(@PathVariable String email) {
        if(tRep.findById(email).isPresent()){
            return tRep.findById(email).get();
        }
        else return null;
    }

    @Override
    public void eliminaTurista(Turista t) {
        tRep.delete(t);
    }

    @Override
    public void eliminaContributor(Contributor c) {
        cRep.delete(c);
    }
}
