package ids.Repository;

import ids.Model.Luogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LuogoRepository extends JpaRepository<Luogo,String> {}
