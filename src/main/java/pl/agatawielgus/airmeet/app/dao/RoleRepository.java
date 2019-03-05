package pl.agatawielgus.airmeet.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.agatawielgus.airmeet.app.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {


}
