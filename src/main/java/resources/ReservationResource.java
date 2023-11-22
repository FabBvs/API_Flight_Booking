package resources;

import beans.Reservation;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import repositories.ReservationRepository;

import java.util.List;

@Path("/reservations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReservationResource extends GenericResource {

    @Inject
    private ReservationRepository repository;

    @Inject
    Validator validator;

    @GET
    public Response getReservations(@QueryParam("flightId") Integer flightId) {
        List<Reservation> list;
        if (flightId == null) {
            list = repository.listAll();
        } else {
            list = repository.findByFlightId(flightId);
        }
        return getOr404(list);
    }

    // Method to retrieve a reservation by its id
    @GET
    @Path("/{id}")
    public Response getReservation(@PathParam("id") Long id) {
        var reservation = repository.findByIdOptional(id).orElse(null);
        return getOr404(reservation);
    }

    // Method to create a reservation
    @POST
    @Transactional
    public Response createReservation(Reservation reservation) {
        var violations = validator.validate(reservation);
        if (violations.isEmpty()) {
            return Response.status(400).entity(new ErrorWrapper(violations)).build();
        }
        try {
            repository.persistAndFlush(reservation);
            return Response.status(201).build();
        } catch (PersistenceException e) {
            return Response.serverError().entity(new ErrorWrapper(e.getMessage())).build();
        }
    }
}