package ids.Servizi;

import ids.Model.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UtenteServizi {
    void save(Turista t);
    void save(Contributor c);

    List<Turista> listaTuristi();
    List<TuristaAutorizzato> listaTuristiAutorizzati();
    List<Contributor> listaContributor();
    List<ContributorAutorizzato> listaContributorAutorizzati();
    void aggiunigUtente(String tipo, String nome, String email, String password);
    Turista trovaTuristaConMail(String email);
    void creaItinerario(Turista turista,String nome, List<Contenuto> luoghi);
    List<Segnalazione> listaSegnalazioni();
    public void partecipaContest(String titolo, String Email);
}