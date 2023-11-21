package beans;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "reservations")
public class Reservation {
    @Id
    @SequenceGenerator(name = "reservations_sequence_in_java_code", sequenceName = "reservations_sequence_in_database", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservations_sequence_in_java_code")
    private Long id; //L'id de la réservation !

    @NotNull(message = "L'id du vol de la réservation ne peut pas être nul !")
    @ManyToOne
    @JoinColumn(name = "flight_id",  nullable = false)
    private Vol flight_id; //L'id du vol de la réservation !

    @NotNull(message = "L'id du passager de la réservation ne peut pas être nul !")
    @ManyToOne
    @JoinColumn(name = "passenger_id",  nullable = false)
    private Passager passenger_id; //L'id du passager de la réservation !
}
