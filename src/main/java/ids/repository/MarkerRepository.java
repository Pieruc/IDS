package ids.repository;

import ids.model.Marker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkerRepository extends JpaRepository<Marker, Integer>{
}
