package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.UserDTO;
import entities.Role;
import entities.User;

import facades.UserFacade;
import utils.EMF_Creator;

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
            user.addRole(userRole);
            user1.addRole(userRole);
//      admin.addRole(adminRole);
            em.persist(userRole);
            em.persist(adminRole);
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
    @Path("users")
    //@RolesAllowed("user")
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