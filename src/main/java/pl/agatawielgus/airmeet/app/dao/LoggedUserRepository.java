package pl.agatawielgus.airmeet.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.agatawielgus.airmeet.app.entity.LoggedInUser;

public interface LoggedUserRepository extends JpaRepository<LoggedInUser, Integer> {

    LoggedInUser findByUsername(String username);

}
