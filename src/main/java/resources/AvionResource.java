package resources;

import beans.Avion;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import repositories.AvionRepository;
import java.util.List;

@Path("/planes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvionResource extends GenericResource {

    @Inject
    private AvionRepository repository; // Identique à => AvionRepository repo = new AvionRepository();

    @Inject
    Validator validator;

    // Méthode pour récupérer tous les avions ou tous les avions en fonctions de leurs compagnies !
    @GET
    public Response getPlanes(@QueryParam("operator")String operator) {
        List<Avion> list;
        if(StringUtils.isBlank(operator)) {
            list = repository.listAll();
        }else{
            list = repository.findByOperator(operator);
        }
        return getOr404(list);
    }

    // Méthode pour récupérer un avion par son id !
    @GET
    @Path("/{id}")
    public Response getPlane(@PathParam("id")Long id) {
        var avion = repository.findByIdOptional(id).orElse(null); //Permet de prendre en compte le fait que la réponse est vide !
        return getOr404(avion);
    }

    // Méthode pour créer un avion !
    @POST
    @Transactional
    public Response createPlane(Avion plane) {
        var violations = validator.validate(plane);
        if(!violations.isEmpty()){ //Si l'avion n'est pas valide !
            return Response.status(400).entity(new ErrorWrapper(violations)).build(); //Erreur au niveau du client !
        }
        try {
            repository.persistAndFlush(plane);
            return Response.status(201).build();
        } catch (PersistenceException e) {
            return Response.serverError().entity(new ErrorWrapper(e.getMessage())).build();
        }
    }

    // Méthode pour mettre à jour un avion par son id !
    @PUT
    @Path("/{id}")
    @Transactional
    public Response updatePlane(@PathParam("id") Long id, Avion updatedPlane) {
        var existingPlane = repository.findByIdOptional(id).orElse(null);

        if (existingPlane == null) {
            return Response.status(404).entity(new ErrorWrapper("L'avion n'existe pas !")).build();
        }

        existingPlane.setOperator(updatedPlane.getOperator());
        existingPlane.setModel(updatedPlane.getModel());
        existingPlane.setRegistration(updatedPlane.getRegistration());
        existingPlane.setCapacity(updatedPlane.getCapacity());

        var violations = validator.validate(existingPlane);
        if (!violations.isEmpty()) {
            return Response.status(400).entity(new ErrorWrapper(violations)).build();
        }

        try {
            repository.persistAndFlush(existingPlane);
            return Response.status(200).entity(existingPlane).build();
        } catch (PersistenceException e) {
            return Response.serverError().entity(new ErrorWrapper(e.getMessage())).build();
        }
    }

    // Méthode pour supprimer un avion par son id !
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletePlane(@PathParam("id") Long id) {
        var plane = repository.findByIdOptional(id).orElse(null);

        if (plane == null) {
            return Response.status(404).entity(new ErrorWrapper("L'avion n'existe pas !")).build();
        }

        try {
            repository.delete(plane);
            return Response.status(204).build();
        } catch (PersistenceException e) {
            return Response.serverError().entity(new ErrorWrapper(e.getMessage())).build();
        }
    }


}