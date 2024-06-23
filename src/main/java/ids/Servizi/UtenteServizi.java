package ids.Servizi;

import ids.Model.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UtenteServizi {
    void save(Turista t);
    void save(Contributor c);
    ResponseEntity<List<Turista>> listaTuristi();
    ResponseEntity<List<Contributor>> listaContributor();
    ResponseEntity<Contributor> ricercaContributorConMail(String email);
    ResponseEntity<Turista> ricercaTuristaConMail(String email);
    boolean addUtente(String tipo, String nome, String email, String password);
    Contributor trovaContributorConMail(String email);
    Turista trovaTuristaConMail(String email);
    ResponseEntity<Contributor> aggiornaContributorConMail(String nome, String email, String password);
    ResponseEntity<Turista> aggiornaTuristaConMail(String nome, String email, String password);
    void eliminaTurista(Turista t);
    void eliminaContributor(Contributor c);
    boolean loginCheck(Request request);
    boolean registerCheck(String tipo,String nome, String email, String password);
    void creaItinerario(Turista turista,String nome);


}
