package ids.Repository;

import ids.Model.TuristaAutorizzato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TuristaAutorizzatoRepository extends JpaRepository<TuristaAutorizzato,String> {
}
