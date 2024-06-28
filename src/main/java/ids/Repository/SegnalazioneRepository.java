package ids.Repository;

import ids.Model.Segnalazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegnalazioneRepository extends JpaRepository<Segnalazione,String> {
}
