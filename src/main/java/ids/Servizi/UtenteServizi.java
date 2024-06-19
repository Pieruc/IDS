package ids.Servizi;

import ids.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UtenteServizi {
    void save(Turista t);
    void save(Contributor c);
    ResponseEntity<List<Turista>> listaTuristi();
    ResponseEntity<List<Contributor>> listaContributor();
    ResponseEntity<Contributor> ricercaContributorConMail(@PathVariable String email);
    ResponseEntity<Turista> ricercaTuristaConMail(@PathVariable String email);
    boolean addUtente(@RequestParam String tipo, @RequestParam String nome, @RequestParam String email, @RequestParam String password);
    Contributor trovaContributorConMail(@PathVariable String email);
    Turista trovaTuristaConMail(@PathVariable String email);
    ResponseEntity<Contributor> aggiornaContributorConMail(@RequestParam String nome, @RequestParam String email, @RequestParam String password);
    ResponseEntity<Turista> aggiornaTuristaConMail(@RequestParam String nome, @RequestParam String email, @RequestParam String password);
    void eliminaTurista(Turista t);
    void eliminaContributor(Contributor c);

}
