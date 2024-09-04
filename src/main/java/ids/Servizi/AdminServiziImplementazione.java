package ids.Servizi;

import ids.Model.*;
import ids.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    UtenteServiziImplementazione uRep;

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
    public List<Contest> listaContest(){
        return coRep.findAll();
    }

    @Override
    public int rimborso() {
        return 0;
    }

    @Override
    public void modificaRuolo(Turista t) {
        if(tARep.findById(t.getEmail()).isEmpty()){
            TuristaAutorizzato tA = new TuristaAutorizzato(t.getNome(), t.getEmail(), t.getPassword());
            tARep.save(tA);
        }
    }

    @Override
    public void modificaRuolo(Contributor c) {
        if(cARep.findById(c.getEmail()).isEmpty()){
            ContributorAutorizzato cA = new ContributorAutorizzato(c.getNome(), c.getEmail(), c.getPassword());
            cARep.save(cA);
        }
    }
}
