package resources;

import beans.Passager;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import repositories.PassagerRepository;

import java.util.List;

@Path("/passengers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PassagerResource extends GenericResource {

    @Inject
    private PassagerRepository repository;

    @Inject
    Validator validator;

    // Method to retrieve all passengers or passengers based on flightId
    @GET
    public Response getPassengers(@QueryParam("Surname") String surnameParameter) {
        List<Passager> list;
        if (StringUtils.isBlank(surnameParameter)) {
            list = repository.listAll();
        } else {
            list = repository.findBySurname(surnameParameter);
        }
        return getOr404(list);
    }

    // Method to retrieve a passenger by its id
    @GET
    @Path("/{id}")
    public Response getPassenger(@PathParam("id") Long id) {
        var passager = repository.findByIdOptional(id).orElse(null);
        return getOr404(passager);
    }

    // Method to create a passenger
    @POST
    @Transactional
    public Response createPassenger(Passager passager) {
        var violations = validator.validate(passager);
        if (violations.isEmpty()) {
            return Response.status(400).entity(new ErrorWrapper(violations)).build();
        }
        try {
            repository.persistAndFlush(passager);
            return Response.status(201).build();
        } catch (PersistenceException e) {
            return Response.serverError().entity(new ErrorWrapper(e.getMessage())).build();
        }
    }
}
