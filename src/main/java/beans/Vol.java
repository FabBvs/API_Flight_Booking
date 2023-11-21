package beans;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "flights")
public class Vol extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "flights_sequence_in_java_code", sequenceName = "flights_sequence_in_database", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vols_sequence_in_java_code")
    private Long id; //L'id du vol !

    @NotBlank(message = "Le numéro du vol ne peut pas être nul !")
    @Column(nullable = false, unique = true)
    private String number; //Le numéro du vol !

    @NotBlank(message = "L'origine du vol ne peut pas être nulle !")
    @Column(nullable = false)
    private String origin; //L'origine du vol !

    @NotBlank(message = "La destination du vol ne peut pas être nulle !")
    @Column(nullable = false)
    private String destination; //La destination du vol !

    @NotBlank(message = "La date de départ du vol ne peut pas être nulle !")
    @Column(nullable = false)
    private LocalDate departure_date; //La date de départ du vol !

    @NotBlank(message = "L'heure de départ du vol ne peut pas être nulle !")
    @Column(nullable = false)
    private LocalTime departure_time; //L'heure de départ du vol !

    @NotBlank(message = "La date d'arrivée du vol ne peut pas être nulle !")
    @Column(nullable = false)
    private LocalDate arrival_date; //La date d'arrivée du vol !

    @NotBlank(message = "L'heure d'arrivée du vol ne peut pas être nulle !")
    @Column(nullable = false)
    private LocalTime arrival_time; //L'heure d'arrivée du vol !

    @NotNull(message="L'id de l'avion du vol ne peut pas être nul !")
    @ManyToOne
    @JoinColumn(name = "plane_id", nullable = false)
    private Avion avion; //L'id de l'avion du vol !
}
