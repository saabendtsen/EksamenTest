package facades;

import dtos.HarbourDTO;
import entities.Boat;
import entities.Harbour;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class HarbourFacade {

    private static EntityManagerFactory emf;
    private static HarbourFacade instance;

    private HarbourFacade(){}

    public static HarbourFacade getHarbourFacade(EntityManagerFactory _emf){
        if (instance == null){
            emf = _emf;
            instance = new HarbourFacade();
        }
        return instance;
    }

    public HarbourDTO createHarbour(HarbourDTO harbourDTO){
        EntityManager em = emf.createEntityManager();
        Harbour harbour = new Harbour(harbourDTO);

        try{
            em.getTransaction().begin();
            em.persist(harbour);
            em.getTransaction().commit();
            return new HarbourDTO(harbour);
        } finally {
            em.close();
        }
    }


    public List<Boat> getBoatsByHarbour(String harbourName){
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();
            Harbour harbour = em.find(Harbour.class, harbourName);
            em.getTransaction().commit();
           return harbour.getBoats();
        } finally {
            em.close();
        }
    }

}
