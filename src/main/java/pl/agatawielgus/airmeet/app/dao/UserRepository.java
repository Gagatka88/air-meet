package pl.agatawielgus.airmeet.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.agatawielgus.airmeet.app.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);


}
