package pl.agatawielgus.airmeet.app.service;

import pl.agatawielgus.airmeet.app.entity.LoggedInUser;
import pl.agatawielgus.airmeet.app.entity.User;
import pl.agatawielgus.airmeet.app.user.MeetingAppUser;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public void save(MeetingAppUser user);

    public void save(User user);

    public void save(LoggedInUser loggedInUser);

    public void delete(LoggedInUser loggedInUser);

    public User findByUsername(String username);

    public LoggedInUser findLoggedInUserByUsername (String username);


}
