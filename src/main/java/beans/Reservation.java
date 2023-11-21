package beans;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Reservation")
public class Reservation {

    @Id
    @SequenceGenerator(name = "reservations_sequence_in_java_code", sequenceName = "reservations_sequence_in_database", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservations_sequence_in_java_code")
    private Long id;

    @NotNull(message = "L'id du vol de la réservation ne peut pas être nul !")
    @ManyToOne
    @JoinColumn(name = "flight_id",  nullable = false)
    private Vol vol; //L'id du vol de la réservation !

    @NotNull(message = "La passenger_id ne peut pas être nulle !")
    @Column(nullable = false)
    private Integer passenger_id; //the identity of the passengers!
}
