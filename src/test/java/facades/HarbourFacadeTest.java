package facades;

import dtos.HarbourDTO;
import entities.Harbour;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    }


    @Test
    void createHarbour() {
        HarbourDTO harbourDTO = new HarbourDTO("Nyhavn","nyhavnvej",3);
        harbourDTO = instance.createHarbour(harbourDTO);
        assertTrue(harbourDTO.getId() != null );

    }

    @Test
    void createBoat(){



    }

}