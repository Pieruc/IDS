package ids.Repository;

import ids.Model.ContributorAutorizzato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributorAutorizzatoRepository extends JpaRepository<ContributorAutorizzato,String> {
}
