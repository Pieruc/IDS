package ids.Servizi;

import ids.Model.*;

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
    List<Itinerario> listaItinerarioByTurista(String email);
    List<Segnalazione> listaSegnalazioni();
    void partecipaContest(String titolo, String email);
    void eliminaAccount(String email);
}