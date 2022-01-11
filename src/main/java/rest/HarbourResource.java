package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.HarbourDTO;
import facades.HarbourFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("/harbour")
public class HarbourResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final HarbourFacade facade = HarbourFacade.getHarbourFacade(EMF);
    @Context
    SecurityContext securityContext;



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }



    @POST
    @Path("/createHarbour")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createHarbour(String newHarbour){
        HarbourDTO harbourDTO = gson.fromJson(newHarbour,HarbourDTO.class);
        harbourDTO = facade.createHarbour(harbourDTO);
        return gson.toJson(harbourDTO);
    }


}