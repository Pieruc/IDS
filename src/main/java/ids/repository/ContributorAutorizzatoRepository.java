package ids.repository;

import ids.model.ContributorAutorizzato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ContributorAutorizzatoRepository {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public ContributorAutorizzatoRepository(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ids.pu");
        this.entityManager = emf.createEntityManager();
    }

    public void addContributorAutorizzato(ContributorAutorizzato c){
        entityManager.getTransaction().begin();
        entityManager.persist(c);
        entityManager.getTransaction().commit();
    }

    public ContributorAutorizzato findContributorAutorizzato(Long id){
        return entityManager.find(ContributorAutorizzato.class,id);
    }

    public void update(ContributorAutorizzato c){
        ContributorAutorizzato contributorDaAggiornare = findContributorAutorizzato(c.getId());
        entityManager.getTransaction().begin();
        contributorDaAggiornare.setNome(c.getNome());
        contributorDaAggiornare.setEmail(c.getEmail());
        contributorDaAggiornare.setPassword(c.getPassword());
        entityManager.getTransaction().commit();
    }

    public void delete(ContributorAutorizzato c){
        entityManager.getTransaction().begin();
        entityManager.remove(c);
        entityManager.getTransaction().commit();
    }

    public void close(){
        this.entityManager.close();
        this.emf.close();
    }

}
