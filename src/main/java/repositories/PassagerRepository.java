package repositories;

import beans.Passager;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.inject.Model;
import java.util.List;

@Model
public class PassagerRepository implements PanacheRepositoryBase<Passager, Long> {

    public List<Passager> findBySurname(String surnameParameter) {
        return find("surname", surnameParameter).list(); // Rechercher tous les passagers par nom de famille !
    }

    public void createPassenger(Passager passager) {
        persist(passager); // Créer un nouveau passager !
    }

    public Passager findByIdentifier(Long id) {
        return findById(id); // Rechercher un passager par ID !
    }
}
