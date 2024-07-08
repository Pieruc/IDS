package ids.Servizi;

import ids.Model.*;
import ids.Repository.*;
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

    @Autowired
    SegnalazioneRepository sRep;

    @Autowired
    LuogoRepository lRep;

    @Autowired
    ContestRepository coRep;

    @Autowired
    TuristaAutorizzatoRepository tARep;

    @Autowired
    ContributorAutorizzatoRepository cARep;

    @Autowired
    PrenotazioneRepository pREp;

    @Override
    public void save(Turista t) {
        tRep.save(t);
    }

    @Override
    public void save(Contributor c) {
        cRep.save(c);
    }

    @Override
    public void save(TuristaAutorizzato tA){
        tARep.save(tA);
    }

    @Override
    public void save(ContributorAutorizzato cA){
        cARep.save(cA);
    }

    @Override
    public void save(Segnalazione s) {
        sRep.save(s);
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
    public ResponseEntity<List<TuristaAutorizzato>> listaTuristiAutorizzati() {
        try{
            List<TuristaAutorizzato> list = new ArrayList<>(tARep.findAll());
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

    @Override
    public ResponseEntity<List<ContributorAutorizzato>> listaContributorAutorizzati() {
        try{
            List<ContributorAutorizzato> list = new ArrayList<>(cARep.findAll());
            if(list.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Turista> ricercaTuristaConMail(String e){
        Optional<Turista> t = tRep.findById(e);
        return t.map(
                turista -> new ResponseEntity<>(turista, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<TuristaAutorizzato> ricercaTuristaAutorizzatoConMail(String e){
        Optional<TuristaAutorizzato> tA = tARep.findById(e);
        return tA.map(
                        turistaAutorizzato -> new ResponseEntity<>(turistaAutorizzato, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Contributor> ricercaContributorConMail(String e){
        Optional<Contributor> c = cRep.findById(e);
        return c.map(
                        contributor -> new ResponseEntity<>(contributor, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ContributorAutorizzato> ricercaContributorAutorizzatoConMail(String e){
        Optional<ContributorAutorizzato> cA = cARep.findById(e);
        return cA.map(
                        contributorAutorizzato -> new ResponseEntity<>(contributorAutorizzato, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public boolean aggiunigUtente(String tipo, String n, String e, String p) {
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
        for(Itinerario i : t.getItinerari()){
            if(i.getProprietario().equals(t)){
                iRep.delete(i);
            }
        }
        for(Contest c : t.getPartecipazioni()){
            if(c.getcPartecipanti().equals(t)){
                coRep.delete(c);
            }
        }
        for(Segnalazione s : t.getSegnalazioni()){
            if(s.getAutore().equals(t)){
                sRep.delete(s);
            }
        }
        tRep.delete(t);
        if(tARep.findById(t.getEmail()).isPresent()){
            TuristaAutorizzato tA = new TuristaAutorizzato(t.getNome(), t.getEmail(), t.getPassword());
            tARep.delete(tA);
        }
    }

    @Override
    public void eliminaContributor(Contributor c) {
        for(Contest p : c.getPartecipazioni()){
            if(p.getcPartecipanti().equals(c)){
                coRep.delete(p);
            }
        }
        cRep.delete(c);
        if(cARep.findById(c.getEmail()).isPresent()){
            ContributorAutorizzato cA = new ContributorAutorizzato(c.getNome(), c.getEmail(), c.getPassword());
            cARep.delete(cA);
        }
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
                aggiunigUtente(tipo,nome,email,password);
                return true;
            } else {
                return false;
            }
        }
        if(tipo.equalsIgnoreCase("contributor")){
            Contributor temp = cRep.findById(email).orElse(null);
            if(temp == null){
                aggiunigUtente(tipo,nome,email,password);
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

    @Override
    public boolean aggiungiSegnalazione(Segnalazione segnalazione){
        try {
            sRep.save(segnalazione);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    public ResponseEntity<List<Segnalazione>> listaSegnalazioni(){
        try{
            List<Segnalazione> list = new ArrayList<>(sRep.findAll());
            if(list.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Segnalazione trovaSegnalazione(String titolo){
        if(sRep.findById(titolo).isPresent()){
            return sRep.findById(titolo).get();
        }
        else return null;
    }

    @Override
    public void eliminaSegnalazione(Segnalazione segnalazione){
        segnalazione.getAutore().getSegnalazioni().remove(segnalazione);
        segnalazione.getLuogo().getSegnalazioni().remove(segnalazione);
        sRep.delete(segnalazione);
    }

    @Override
    public boolean verificaSegnalazione(String titolo){
        Segnalazione temp = sRep.findById(titolo).orElse(null);
        if(temp != null){
            temp.setStato(true);
            sRep.save(temp);
            return true;
        }
        return false;
    }

    @Override
    public Contest partecipaAlContest(String titolo, Request request) {
        Contest contest = coRep.findById(titolo).orElseThrow(() -> new RuntimeException("Contest non trovato"));
        String tipo = request.getTipo();
        if(contest.getEsclusivita()) {
            if (tipo.equalsIgnoreCase("turista")) {
                contest.addTuristaPartecipante(tRep.findById(request.getEmail()).orElseThrow(() -> new RuntimeException("Turista non trovato")));
                return coRep.save(contest);
            }
            if (tipo.equalsIgnoreCase("contributor")) {
                contest.addContributorPartecipante(cRep.findById(request.getEmail()).orElseThrow(() -> new RuntimeException("Contributor non trovato")));
                return coRep.save(contest);
            }
        } else {
            if (tipo.equalsIgnoreCase("turistaautorizzato")) {
                contest.addTuristaAutorizzatoPartecipante(tARep.findById(request.getEmail()).orElseThrow(() -> new RuntimeException("Turista non trovato")));
                return coRep.save(contest);
            }
            if (tipo.equalsIgnoreCase("contributorautorizzato")) {
                contest.addContributorAutorizzatoPartecipante(cARep.findById(request.getEmail()).orElseThrow(() -> new RuntimeException("Contributor non trovato")));
                return coRep.save(contest);
            }
        }
        return null;
    }

    @Override
    public boolean creaPrenotazione(){
        Prenotazione p = new Prenotazione();
        pREp.save(p);
        return true;
    }
}