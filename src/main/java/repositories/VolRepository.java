package repositories;

import beans.Avion;
import beans.Vol;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.inject.Model;

import java.util.List;

@Model
public class VolRepository implements PanacheRepositoryBase<Vol, Long> {

    public List<Vol> findByNumber(String numberParameter) {
        return find("number", numberParameter).list(); // Rechercher les vols par leurs numéros !
    }

    public Vol findById(Long idParameter) {
        return findById(idParameter); //Rechercher un avion par son id !
    }

    public void createFlight() {
        persist((Iterable<Vol>) new Avion()); //Créer un vol !
    }
}
