package ids.Servizi;

import ids.Model.*;

import java.util.List;

public interface AdminServizi {
    void creaContest(Contest contest);
    void chiudiContest(String titolo);
    List<Contest> listaContest();
    List<Utente> getPartecipantiByTitolo(String titolo);
    int rimborso();
    void modificaRuolo(Turista t);
    void modificaRuolo(Contributor c);
}
