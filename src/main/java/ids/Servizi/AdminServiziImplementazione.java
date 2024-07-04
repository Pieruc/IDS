package ids.Servizi;

import ids.Model.*;
import ids.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiziImplementazione implements AdminServizi{

    @Autowired
    ContestRepository coRep;

    @Autowired
    TuristaRepository tRep;

    @Autowired
    ContributorRepository cRep;

    @Autowired
    ContributorAutorizzatoRepository cARep;

    @Autowired
    TuristaAutorizzatoRepository tARep;

    @Override
    public void creaContest(Contest contest) {
        coRep.save(contest);
    }

    @Override
    public void chiudiContest(String titolo) {
        Contest contest = coRep.findById(titolo).orElseThrow(()->new RuntimeException("Contest non trovato"));
        if(!contest.getEsclusivita()){
            for(Turista t : contest.gettPartecipanti()){
                contest.removeTuristaPartecipante(t);
            }
            for(Contributor c : contest.getcPartecipanti()){
                contest.removeContributorPartecipante(c);
            }
        } else {
            for(TuristaAutorizzato tA : contest.gettAPartecipanti()){
                contest.removeTuristaAutorizzatoPartecipante(tA);
            }
            for(ContributorAutorizzato cA : contest.getcAPartecipanti()){
                contest.removeContributorAutorizzatoPartecipante(cA);
            }
        }
        coRep.deleteById(titolo);
    }

    @Override
    public ResponseEntity<List<Contest>> listaContest() {
        try{
            List<Contest> listaContest = new ArrayList<>(coRep.findAll());
            if(listaContest.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(listaContest,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public int rimborso() {
        return 0;
    }

    @Override
    public void modificaRuolo(Turista t) {
        tRep.delete(tRep.findById(t.getEmail()).orElseThrow(()-> new RuntimeException("Turista non trovato")));
        TuristaAutorizzato tA = new TuristaAutorizzato(t.getNome(),t.getEmail(),t.getPassword());
        tARep.save(tA);
    }

    @Override
    public void modificaRuolo(Contributor c) {

    }
}
