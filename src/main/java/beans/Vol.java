package beans;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
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
public class Vol extends PanacheEntity {
    @Id
    @SequenceGenerator(name = "vols_sequence_in_java_code", sequenceName = "vols_sequence_in_database", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vols_sequence_in_java_code")
    private Long id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private Date departure_date;

    @Column(nullable = false)
    private Time departure_time;

    @Column(nullable = false)
    private Date arrival_date;

    @Column(nullable = false)
    private Time arrival_time;

    @Id //A faire !
    @Column(nullable = false)
    private Integer plane_id;

}
