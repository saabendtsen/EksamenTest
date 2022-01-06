package facades;

import entities.Harbour;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

class HarbourFacadeTest {

    private static EntityManagerFactory emf;
    private static HarbourFacade instance;

    Harbour harbour;




    @BeforeAll
    public static void setUpClass(){
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        instance = HarbourFacade.getHarbourFacade(emf);
    }

    @AfterAll
    public static void CleanDB(){
        //Todo Clear DB;
    }


    @BeforeEach
    public void setUp(){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            harbour = new Harbour("Nybro Havn","Lige nede ved kanten",3)
        }

    }



}