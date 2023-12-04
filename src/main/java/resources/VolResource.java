package resources;

import beans.Vol;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import repositories.AvionRepository;
import repositories.VolRepository;
import java.util.List;


@Path("/flights")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VolResource extends GenericResource {

    @Inject
    private VolRepository repository; // Identique à => VolRepository repo = new VolRepository();
    @Inject
    private AvionRepository avionRepository;

    @Inject
    Validator validator;

    // Méthode pour récupérer tous les vols ou tous les vols en fonctions de leurs destinations !
    @GET
    public Response getFlights(@QueryParam("destination") String destination) {
        List<Vol> list;
        if (StringUtils.isBlank(destination)) {
            list = repository.listAll();
        } else {
            list = repository.findByDestination(destination);
        }
        return getOr404(list);
    }

    // Méthode pour récupérer un avion par son id !
    @GET
    @Path("/{id}")
    public Response getFlight(@PathParam("id")Long id) {
        var vol = repository.findByIdOptional(id).orElse(null); //Permet de prendre en compte le fait que la réponse est vide !
        return getOr404(vol);
    }

    // Méthode pour créer un vol !
    @POST
    @Transactional
    public Response createFlight(Vol vol) {
        var violations = validator.validate(vol);
        if(!violations.isEmpty()){ //Si l'avion n'est pas valide !
            return Response.status(400).entity(new ErrorWrapper(violations)).build(); //Erreur au niveau du client !
        }

        var idAvion = avionRepository.findById(vol.getPlane_id().getId());
        if(!idAvion.isPersistent()){ //Si l'id de l'avion n'est pas trouvé !
            return Response.status(400).entity(new ErrorWrapper(String.valueOf(idAvion))).build(); //Erreur au niveau du client !
        }else {
            vol.setPlane_id(idAvion);
        }

        try {
            repository.persistAndFlush(vol);
            return Response.status(201).build();
        } catch (PersistenceException e) {
            return Response.serverError().entity(new ErrorWrapper(e.getMessage())).build();
        }
    }

    // Méthode pour mettre à jour un vol par son id
    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateFlight(@PathParam("id") Long id, Vol updatedVol) {
        var existingVol = repository.findByIdOptional(id).orElse(null);
        if (existingVol == null) {
            return Response.status(404).entity(new ErrorWrapper("Le vol n'existe pas !")).build();
        }

        var idAvion = avionRepository.findByIdOptional(updatedVol.getPlane_id().getId()).orElse(null);
        if (idAvion == null) {
            return Response.status(404).entity(new ErrorWrapper("Le vol n'existe pas !")).build();
        }

        var violations = validator.validate(updatedVol);
        if (!violations.isEmpty()) {
            return Response.status(400).entity(new ErrorWrapper(violations)).build();
        }

        existingVol.setNumber(updatedVol.getNumber());
        existingVol.setOrigin(updatedVol.getOrigin());
        existingVol.setDestination(updatedVol.getDestination());
        existingVol.setDeparture_date(updatedVol.getDeparture_date());
        existingVol.setDeparture_time(updatedVol.getDeparture_time());
        existingVol.setArrival_date(updatedVol.getArrival_date());
        existingVol.setArrival_time(updatedVol.getArrival_time());
        existingVol.setPlane_id(updatedVol.getPlane_id());

        try {
            repository.persistAndFlush(existingVol);
            return Response.status(200).entity(existingVol).build();
        } catch (PersistenceException e) {
            return Response.serverError().entity(new ErrorWrapper(e.getMessage())).build();
        }
    }

    // Méthode pour supprimer un vol par son id
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteFlight(@PathParam("id") Long id) {
        var vol = repository.findByIdOptional(id).orElse(null);

        if (vol == null) {
            return Response.status(404).entity(new ErrorWrapper("Le vol n'existe pas !")).build();
        }

        try {
            repository.delete(vol);
            return Response.status(204).build();
        } catch (PersistenceException e) {
            return Response.serverError().entity(new ErrorWrapper(e.getMessage())).build();
        }
    }
}