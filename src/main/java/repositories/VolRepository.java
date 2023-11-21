package repositories;

import beans.Vol;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.inject.Model;

@Model
public class VolRepository implements PanacheRepositoryBase<Vol, Long> {
}
