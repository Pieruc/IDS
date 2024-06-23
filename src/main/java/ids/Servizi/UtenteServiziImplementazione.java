package ids.Servizi;

import ids.Model.*;
import ids.Repository.ContributorRepository;
import ids.Repository.ItinerarioRepository;
import ids.Repository.TuristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UtenteServiziImplementazione implements UtenteServizi {

    @Autowired
    TuristaRepository tRep;

    @Autowired
    ContributorRepository cRep;

    @Autowired
    ItinerarioRepository iRep;

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

    public ResponseEntity<Turista> ricercaTuristaConMail(String e){
        Optional<Turista> t = tRep.findById(e);
        return t.map(
                turista -> new ResponseEntity<>(turista, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Contributor> ricercaContributorConMail(String e){
        Optional<Contributor> c = cRep.findById(e);
        return c.map(
                        contributor -> new ResponseEntity<>(contributor, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public boolean addUtente(String tipo, String n, String e, String p) {
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
    public Contributor trovaContributorConMail(String email) {
        if(cRep.findById(email).isPresent()){
            return cRep.findById(email).get();
        }
        else return null;
    }

    @Override
    public Turista trovaTuristaConMail(String email) {
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

    @Override
    public boolean loginCheck(Request request) {
        String tipo = request.getTipo();
        if(tipo.equalsIgnoreCase("turista")){
            Turista temp = tRep.findById(request.getEmail()).orElse(null);
            return temp != null &&
                    temp.getNome().equalsIgnoreCase(request.getNome()) &&
                    temp.getPassword().equalsIgnoreCase(request.getPassword());
        } else if(tipo.equalsIgnoreCase("contributor")){
            Contributor temp = cRep.findById(request.getEmail()).orElse(null);
            return temp != null &&
                    temp.getNome().equalsIgnoreCase(request.getNome()) &&
                    temp.getPassword().equalsIgnoreCase(request.getPassword());
        }
        return false;
    }

    @Override
    public boolean registerCheck(String tipo, String nome, String email, String password) {
        if(tipo.equalsIgnoreCase("turista")){
            Turista temp = tRep.findById(email).orElse(null);
            if(temp == null){
                addUtente(tipo,nome,email,password);
                return true;
            } else {
                return false;
            }
        }
        if(tipo.equalsIgnoreCase("contributor")){
            Contributor temp = cRep.findById(email).orElse(null);
            if(temp == null){
                addUtente(tipo,nome,email,password);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    @Override
    public void creaItinerario(Turista turista, String nome) {
        iRep.save(new Itinerario(nome,turista));
    }
}
