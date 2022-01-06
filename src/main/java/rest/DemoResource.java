package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.UserDTO;
import entities.Role;
import entities.User;
import entities.WatchList;
import facades.UserFacade;
import utils.AddLikesToDB;
import utils.EMF_Creator;
import utils.HttpUtils;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.List;

@Path("/info")
public class DemoResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    @Context
    private UriInfo context;
    private final UserFacade facade = UserFacade.getUserFacade(EMF);
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    //Create Users on Endpoint
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("createusers")
    public String createUsers() {

        EntityManager em = EMF.createEntityManager();

        User user = new User("user", "user1");
        User user1 = new User("user1", "user1");
//        User admin = new User("admin", "admin1");

        try {
            em.getTransaction().begin();
            Role userRole = new Role("user");
            Role adminRole = new Role("admin");
            WatchList watchList = new WatchList("tt4972582"); // Split
            WatchList watchList1 = new WatchList("tt11126994"); // Arcane
            user.addRole(userRole);
            user1.addRole(userRole);
//      admin.addRole(adminRole);
            user.addToWatchList(watchList);
            user.addToWatchList(watchList1);
            user1.addToWatchList(watchList);
            user1.addToWatchList(watchList1);
            em.persist(userRole);
            em.persist(adminRole);
            em.persist(watchList);
            em.persist(watchList1);
            em.persist(user);
            em.persist(user1);
//      em.persist(admin);
            em.getTransaction().commit();
            return "Users Created!";
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("createlikes")
    public String createlikes(){
       AddLikesToDB a = new AddLikesToDB();

        a.addlikeToMovie("tt0420233",100);
        a.addlikeToMovie("tt0376606",90);
        a.addlikeToMovie("tt2092588",80);
        a.addlikeToMovie("tt7631146",70);
        a.addlikeToMovie("tt11012066",60);
        a.addlikeToMovie("tt0486725",50);
        a.addlikeToMovie("tt3480822",40);
        a.addlikeToMovie("tt2085059",30);
        a.addlikeToMovie("tt4154664",20);
        a.addlikeToMovie("tt7667038",10);

        return "Dummy likes created";
    }

    @GET
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() {
        String thisuser = securityContext.getUserPrincipal().getName();
        String welcome = "Welcome "+ thisuser;
        return gson.toJson(welcome);
    }

    @GET
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
        String welcome = "Welcome "+ thisuser;
        return gson.toJson(welcome);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin/users")
    @RolesAllowed("admin")
    public String getAllUsers() {
        List<UserDTO> userDTOList = facade.getAllUsers();
        return gson.toJson(userDTOList);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("createuser")
    @Consumes(MediaType.APPLICATION_JSON)
    public String createUser(String newUser) {
        UserDTO userDTO = gson.fromJson(newUser, UserDTO.class);
        userDTO = facade.createUser(userDTO);
        System.out.println("User: "+ newUser + " Created");
        return gson.toJson(userDTO);
    }

    @DELETE
    @Path("admin/deleteuser/{name}")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser(@PathParam("name") String name) {
        UserDTO userDTO = facade.deleteUser(name);

        System.out.println("User: " + name + " Deleted");
        return gson.toJson(userDTO);
    }



    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("edituser")
    @Consumes(MediaType.APPLICATION_JSON)
    public String editUser(String newUser) {
        UserDTO userDTO = gson.fromJson(newUser, UserDTO.class);
        userDTO = facade.updateUser(userDTO);
        System.out.println("DTO: "+ userDTO.getUsername()+" - "+userDTO.getPassword());
        System.out.println("String: "+ newUser);
        return gson.toJson(userDTO);
    }
}