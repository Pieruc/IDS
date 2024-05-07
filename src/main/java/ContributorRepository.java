import ids.model.Contributor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ContributorRepository {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public ContributorRepository(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ids.pu");
        this.entityManager = emf.createEntityManager();
    }

    public void addContributor(Contributor c){
        entityManager.getTransaction().begin();
        entityManager.persist(c);
        entityManager.getTransaction().commit();
    }

    public Contributor findContributor(Long id){
        return entityManager.find(Contributor.class,id);
    }

    public void update(Contributor c){
        Contributor contributorDaAggiornare = findContributor(c.getId());
        entityManager.getTransaction().begin();
        contributorDaAggiornare.setNome(c.getNome());
        contributorDaAggiornare.setEmail(c.getEmail());
        contributorDaAggiornare.setPassword(c.getPassword());
        entityManager.getTransaction().commit();
    }

    public void delete(Contributor c){
        entityManager.getTransaction().begin();
        entityManager.remove(c);
        entityManager.getTransaction().commit();
    }

    public void close(){
        this.entityManager.close();
        this.emf.close();
    }

}
