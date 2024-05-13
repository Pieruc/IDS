import ids.model.TuristaAutorizzato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TuristaAutorizzatoRepository {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public TuristaAutorizzatoRepository(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ids.pu");
        this.entityManager = emf.createEntityManager();
    }

    public void addTuristaAutorizzato(TuristaAutorizzato t){
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.getTransaction().commit();
    }

    public TuristaAutorizzato findTuristaAutorizzato(Long id){
        return entityManager.find(TuristaAutorizzato.class,id);
    }

    public void update(TuristaAutorizzato t){
        TuristaAutorizzato turistaDaAggiornare = findTuristaAutorizzato(t.getId());
        entityManager.getTransaction().begin();
        turistaDaAggiornare.setNome(t.getNome());
        turistaDaAggiornare.setEmail(t.getEmail());
        turistaDaAggiornare.setPassword(t.getPassword());
        entityManager.getTransaction().commit();
    }

    public void delete(TuristaAutorizzato t){
        entityManager.getTransaction().begin();
        entityManager.remove(t);
        entityManager.getTransaction().commit();
    }

    public void close(){
        this.entityManager.close();
        this.emf.close();
    }

}
