import ids.model.Contributor;
import ids.model.Turista;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Repository {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public Repository(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ids.pu");
        this.entityManager = emf.createEntityManager();
    }

    public void add(Object o){
        entityManager.getTransaction().begin();
        entityManager.persist(o);
        entityManager.getTransaction().commit();
    }

    public Object find(Long id){
        return entityManager.find(Contributor.class,id);
    }

    public void update(Object o){
        if(o instanceof Contributor){
            Contributor c = (Contributor) o;
            Contributor contributorDaAggiornare = (Contributor) find(c.getId());
            entityManager.getTransaction().begin();
            contributorDaAggiornare.setNome(c.getNome());
            contributorDaAggiornare.setEmail(c.getEmail());
            contributorDaAggiornare.setPassword(c.getPassword());
            entityManager.getTransaction().commit();
        }
        else if(o instanceof Turista){
            Turista t = (Turista) o;
            Turista turistaDaAggiornare = (Turista) find(t.getId());
            entityManager.getTransaction().begin();
            turistaDaAggiornare.setNome(t.getNome());
            turistaDaAggiornare.setEmail(t.getEmail());
            turistaDaAggiornare.setPassword(t.getPassword());
            entityManager.getTransaction().commit();
        }
    }

    public void delete(Object o){
        entityManager.getTransaction().begin();
        entityManager.remove(o);
        entityManager.getTransaction().commit();
    }

    public void close(){
        this.entityManager.close();
        this.emf.close();
    }


}
