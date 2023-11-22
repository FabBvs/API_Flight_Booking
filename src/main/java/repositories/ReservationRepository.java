package repositories;

import beans.Reservation;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.inject.Model;
import java.util.List;

@Model
public class ReservationRepository implements PanacheRepositoryBase<Reservation, Long> {

    public List<Reservation> findByFlightId(Integer flightId) {
        return find("flight_id", flightId).list(); // Rechercher toutes les réservations par flight_id !
    }

    public void createReservation(Reservation reservation) {
        persist(reservation); // Créer une nouvelle réservation !
    }

    public Reservation findByIdentifier(Long id) {
        return findById(id); // Rechercher une réservation par ID !
    }
}

