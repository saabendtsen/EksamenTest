package facades;

import dtos.MovieDTO;
import entities.MovieLikes;
import entities.RenameMe;
import entities.Role;
import entities.User;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;
    private static User user;
    private static User admin;
    private static User both;



    public MovieFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = MovieFacade.getMovieFacade(emf);
//        EntityManager em = emf.createEntityManager();
//
//        try {
//            em.getTransaction().begin();
//            user = new User("user1", "user1");
//            admin = new User("admin1", "admin1");
//            Role userRole = new Role("user");
//            Role adminRole = new Role("admin");
//            user.addRole(userRole);
//            admin.addRole(adminRole);
//            em.persist(userRole);
//            em.persist(adminRole);
//            em.persist(user);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test

    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            //Delete existing users and roles to get a "fresh" database
            em.createQuery("delete from User").executeUpdate();
            em.createQuery("delete from Role").executeUpdate();

            Role userRole = new Role("user");
            Role adminRole = new Role("admin");
            user = new User("user", "user1");
            user.addRole(userRole);
            admin = new User("admin", "admin1");
            admin.addRole(adminRole);
            both = new User("user_admin", "user_admin1");
            both.addRole(userRole);
            both.addRole(adminRole);
            em.persist(userRole);
            em.persist(adminRole);
            em.persist(user);
            em.persist(admin);
            em.persist(both);
            //System.out.println("Saved test data to database");
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    void addlikeToMovie() {


        MovieLikes movieLikes = facade.addlikeToMovie("tt4972582");
        long before = movieLikes.getQuantity();
        movieLikes = facade.addlikeToMovie("tt4972582");
        long after = movieLikes.getQuantity();
        assertEquals(before,after-1);
    }

    @Test
    void testWatchLater() throws Exception {
        String actual = facade.addWatchLater(user.getUsername(),"tt4972582");
        assertTrue(actual.length()>0);

        List<MovieDTO> list = facade.getWatchLaterList(user.getUsername());
        assertEquals(list.size(),1);

        facade.deleteWatchLater(user.getUsername(),"tt4972582");
        list = facade.getWatchLaterList(user.getUsername());

        assertEquals(list.size(),0);

    }


    @Test
    void getTopLikedList() throws Exception {

        for (int i = 0; i < 10; i++) {
            facade.addlikeToMovie("tt4972582");
        }
        List<MovieDTO> list = facade.getTopLikedList();
        assertTrue(list.get(0).getId().equals("tt4972582"));
    }
}