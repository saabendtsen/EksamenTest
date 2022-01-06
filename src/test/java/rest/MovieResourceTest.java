package rest;

import dtos.ImdbResponseDTO;
import dtos.MovieDTO;
import entities.MovieLikes;
import entities.Role;
import entities.User;
import facades.MovieFacade;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

class MovieResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.createQuery("delete from User").executeUpdate();
            em.createQuery("delete from Role").executeUpdate();

            em.getTransaction().commit();
        }finally {
            em.close();
        }


        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
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
            User user = new User("user", "user1");
            user.addRole(userRole);
            User admin = new User("admin", "admin1");
            admin.addRole(adminRole);
            User both = new User("user_admin", "user_admin1");
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

    //This is how we hold on to the token after login, similar to that a client must store the token somewhere
    private static String securityToken;

    //Utility method to login and set the returned securityToken
    private static void login(String role, String password) {
        String json = String.format("{username: \"%s\", password: \"%s\"}", role, password);
        securityToken = given()
                .contentType("application/json")
                .body(json)
                //.when().post("/api/login")
                .when().post("/login")
                .then()
                .extract().path("token");
//        System.out.println("TOKEN ---> " + securityToken);
    }

    private void logOut() {
        securityToken = null;
    }

    @Test
    public void serverIsRunning() {
        given().when().get("/info").then().statusCode(200);
    }

    @Test
    void getTitle() {
        List<MovieDTO> movieDTOS;
        movieDTOS = given()
                .contentType("application/json")
                .when()
                .get("/movie/title/Frozen")
                .then().statusCode(200)
                .extract().body().jsonPath().getList("", MovieDTO.class);

            assertTrue(movieDTOS != null);
            assertEquals(movieDTOS.get(0).getMovieName(),"Frozen Planet");
            assertTrue(movieDTOS.size() == 3);
    }

    @Test
    void addLikeMovie() {
        login("user","user1");
        MovieLikes movieLikes;
         movieLikes = given()
                .contentType("application/json")
                 .header("x-access-token", securityToken)
                .when()
                .post("/movie/like/tt2294629")
                .then().statusCode(200)
                 .extract().body().jsonPath().getObject("",MovieLikes.class);

        Long before = movieLikes.getQuantity();

        movieLikes = given()
                .contentType("application/json")
                .header("x-access-token", securityToken)
                .when()
                .post("/movie/like/tt2294629")
                .then().statusCode(200)
                .extract().body().jsonPath().getObject("",MovieLikes.class);

        Long after = movieLikes.getQuantity();

        assertEquals(after,before+1);

    }

    @Test
    void getLikedMovies() {
        login("user","user1");
        given()
                .contentType("application/json")
                .header("x-access-token", securityToken)
                .when()
                .post("/movie/like/tt2294629")
                .then().statusCode(200);
        given()
                .contentType("application/json")
                .header("x-access-token", securityToken)
                .when()
                .post("/movie/like/tt1323045")
                .then().statusCode(200);

        List<MovieDTO> movieDTOS;
        movieDTOS = given()
                .contentType("application/json")
                .header("x-access-token", securityToken)
                .when()
                .get("/movie/like")
                .then().statusCode(200)
                .extract().body().jsonPath().getList("",MovieDTO.class);

        assertNotNull(movieDTOS);
        assertTrue(movieDTOS.size() >= 2);
    }

    @Test
    void getWatchLater() {
        login("user","user1");

        given()
                .contentType("application/json")
                .header("x-access-token", securityToken)
                .when()
                .post("/movie/watchlater/tt2294629")
                .then().statusCode(200);

        given()
                .contentType("application/json")
                .header("x-access-token", securityToken)
                .when()
                .post("/movie/watchlater/tt1323045")
                .then().statusCode(200);

        List<MovieDTO> movieDTO;
        movieDTO = given()
                .contentType("application/json")
                .header("x-access-token", securityToken)
                .when()
                .get("/movie/watchlater")
                .then().statusCode(200)
                .extract().body().jsonPath().getList("",MovieDTO.class);

        assertNotNull(movieDTO.size());
        assertTrue(movieDTO.size() >= 2);
    }

    @Test
    void addWatchLater() {
        login("user","user1");
        given()
                .contentType("application/json")
                .header("x-access-token", securityToken)
                .when()
                .post("/movie/watchlater/tt1323045")
                .then().statusCode(200);

    }

    @Test
    void deleteWatchLater() {
    login("user","user1");
        given()
                .contentType("application/json")
                .header("x-access-token", securityToken)
                .when()
                .post("/movie/watchlater/tt1323045")
                .then().statusCode(200);
        given()
                .contentType("application/json")
                .header("x-access-token", securityToken)
                .when()
                .delete("/movie/watchlater/tt1323045")
                .then().statusCode(200);
    }

}