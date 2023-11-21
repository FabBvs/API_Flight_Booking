package beans;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Time;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "flights")
public class Vol extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "vols_sequence_in_java_code", sequenceName = "vols_sequence_in_database", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vols_sequence_in_java_code")
    private Long id; //L'id du vol !

    @NotBlank(message = "Le numéro du vol ne peut pas être nul !")
    @Column(nullable = false)
    private String number; //Le numéro du vol !

    @NotBlank(message = "L'origine du vol ne peut pas être nulle !")
    @Column(nullable = false)
    private String origin; //L'origine du vol !

    @NotBlank(message = "La destination du vol ne peut pas être nulle !")
    @Column(nullable = false)
    private String destination; //La destination du vol !

    @NotBlank(message = "La date de départ du vol ne peut pas être nulle !")
    @Column(nullable = false)
    private Date departure_date; //La date de départ du vol !

    @NotBlank(message = "L'heure de départ du vol ne peut pas être nulle !")
    @Column(nullable = false)
    private Time departure_time; //L'heure de départ du vol !

    @NotBlank(message = "La date d'arrivée du vol ne peut pas être nulle !")
    @Column(nullable = false)
    private Date arrival_date; //La date d'arrivée du vol !

    @NotBlank(message = "L'heure d'arrivée du vol ne peut pas être nulle !")
    @Column(nullable = false)
    private Time arrival_time; //L'heure d'arrivée du vol !

    @ManyToOne
    @JoinColumn(name = "plane_id", nullable = false) // Nom de la colonne de jointure dans la table Vol
    private Avion avion; //L'id de l'avion du vol !
}
