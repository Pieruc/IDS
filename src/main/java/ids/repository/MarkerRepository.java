package ids.repository;

import ids.model.Marker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MarkerRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public MarkerRepository(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ids.pu");
        this.entityManager = emf.createEntityManager();
    }

    public void addMarker(Marker m){
        entityManager.getTransaction().begin();
        entityManager.persist(m);
        entityManager.getTransaction().commit();
    }

    public Marker findMarker(Long id){
        return entityManager.find(Marker.class,id);
    }

    public void update(Marker m){
        Marker mapMarkerDaAggiornare = findMarker(m.getId());
        entityManager.getTransaction().begin();
        mapMarkerDaAggiornare.setLatitude(m.getLatitude());
        mapMarkerDaAggiornare.setLongitude(m.getLongitude());
        mapMarkerDaAggiornare.setTitle(m.getTitle());
        mapMarkerDaAggiornare.setDescription(m.getDescription());
        mapMarkerDaAggiornare.setImageUrl(m.getImageUrl());
        entityManager.getTransaction().commit();
    }

    public void delete(Marker m){
        entityManager.getTransaction().begin();
        entityManager.remove(m);
        entityManager.getTransaction().commit();
    }

    public void close(){
        this.entityManager.close();
        this.emf.close();
    }
}
