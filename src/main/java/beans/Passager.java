package beans;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "passengers")
public class Passager {
    @Id
    @SequenceGenerator(name = "passengers_sequence_in_java_code", sequenceName = "passengers_sequence_in_database", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passengers_sequence_in_java_code")
    private Long id; //L'id du passager !

    @NotBlank(message = "Le nom de famille du passager ne peut pas être nul !")
    @Column(nullable = false)
    private String surname; //Le nom de famille du passager !

    @NotBlank(message = "Le prénom du passager ne peut pas être nul !")
    @Column(nullable = false)
    private String firstname; //Le prénom du passager !

    @NotBlank(message = "L'email du passager ne peut pas être nul !")
    @Column(nullable = false)
    private String email_address; //L'email du passager !
}
