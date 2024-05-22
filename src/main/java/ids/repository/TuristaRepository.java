package ids.repository;

import ids.model.Turista;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TuristaRepository {
    private  EntityManager entityManager;
    private EntityManagerFactory emf;

    public TuristaRepository(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ids.pu");
        this.entityManager = emf.createEntityManager();
    }

    public void addTurista(Turista t){
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.getTransaction().commit();
    }

    public Turista findTurista(Long id){
        return entityManager.find(Turista.class,id);
    }

    public void update(Turista t){
        Turista turistaDaAggiornare = findTurista(t.getId());
        entityManager.getTransaction().begin();
        turistaDaAggiornare.setNome(t.getNome());
        turistaDaAggiornare.setEmail(t.getEmail());
        turistaDaAggiornare.setPassword(t.getPassword());
        entityManager.getTransaction().commit();
    }

    public void delete(Turista t){
        entityManager.getTransaction().begin();
        entityManager.remove(t);
        entityManager.getTransaction().commit();
    }

    public void close(){
        this.entityManager.close();
        this.emf.close();
    }
}
