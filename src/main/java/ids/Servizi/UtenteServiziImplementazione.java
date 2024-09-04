package ids.Servizi;

import ids.Model.*;
import ids.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

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
    ContestRepository coRep;

    @Autowired
    TuristaAutorizzatoRepository tARep;

    @Autowired
    ContributorAutorizzatoRepository cARep;

    @Autowired
    ContenutoRepository mRep;

    @Override
    public void save(Turista t) {
        tRep.save(t);
    }

    @Override
    public void save(Contributor c) {
        cRep.save(c);
    }

    @Override
    public List<Turista> listaTuristi(){
        return tRep.findAll();
    }

    @Override
    public List<TuristaAutorizzato> listaTuristiAutorizzati(){
        return tARep.findAll();
    }

    @Override
    public List<Contributor> listaContributor(){
        return cRep.findAll();
    }

    @Override
    public List<ContributorAutorizzato> listaContributorAutorizzati(){
        return cARep.findAll();
    }

    @Override
    public void aggiunigUtente(String tipo, String n, String e, String p) {
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
        } catch (Exception ex){
        }
    }

    @Override
    public Turista trovaTuristaConMail(String email) {
        if(tRep.findById(email).isPresent()){
            return tRep.findById(email).get();
        }
        else return null;
    }

    @Override
    public void creaItinerario(Turista turista, String nome, List<Contenuto> luoghi) {
        iRep.save(new Itinerario(nome, turista, luoghi));
    }

    @Override
    public List<Segnalazione> listaSegnalazioni(){
        return sRep.findAll();
    }

    @Override
    public void partecipaContest(String titolo, String email){
        if(coRep.findById(titolo).isPresent()){
            Contest contest = coRep.findById(titolo).get();
            if(contest.getEsclusivita()){
                if(tARep.findById(email).isPresent()){
                    contest.addTuristaAutorizzatoPartecipante(tARep.findById(email).get());
                    coRep.save(contest);
                }
                if(cARep.findById(email).isPresent()){
                    contest.addContributorAutorizzatoPartecipante(cARep.findById(email).get());
                    coRep.save(contest);
                }
            }
            if(!contest.getEsclusivita()){
                if(tRep.findById(email).isPresent()){
                    contest.addTuristaPartecipante(tRep.findById(email).get());
                    coRep.save(contest);
                }
                if(cRep.findById(email).isPresent()){
                    contest.addContributorPartecipante(cRep.findById(email).get());
                    coRep.save(contest);
                }
            }
        }
    }

}