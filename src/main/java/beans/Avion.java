package beans;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "planes")
public class Avion extends PanacheEntity {
    @Id
    @SequenceGenerator(name = "planes_sequence_in_java_code", sequenceName = "planes_sequence_in_database", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "planes_sequence_in_java_code")
    private Long id;

    @Column(nullable = false)
    private String operator;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false, unique = true)
    private String registration; //Immatriculation
    @Column(nullable = false)
    private Integer capacity;

}
