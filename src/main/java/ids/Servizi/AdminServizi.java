package ids.Servizi;

import ids.Model.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminServizi {
    void creaContest(Contest contest);
    void chiudiContest(String titolo);
    ResponseEntity<List<Contest>> listaContest();
    int rimborso();
    void modificaRuolo(Turista t);
    void modificaRuolo(Contributor c);
}
