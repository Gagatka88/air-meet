package pl.agatawielgus.airmeet.app.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.agatawielgus.airmeet.app.dao.LoggedUserRepository;
import pl.agatawielgus.airmeet.app.dao.RoleRepository;
import pl.agatawielgus.airmeet.app.dao.UserRepository;
import pl.agatawielgus.airmeet.app.entity.LoggedInUser;
import pl.agatawielgus.airmeet.app.entity.Role;
import pl.agatawielgus.airmeet.app.entity.User;
import pl.agatawielgus.airmeet.app.user.MeetingAppUser;

import javax.transaction.Transactional;
import java.util.List;

@Service
@FieldDefaults(level=AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    LoggedUserRepository loggedUserRepository;
    RoleRepository roleRepository;

   @Autowired
    BCryptPasswordEncoder passwordEncoder;

    final String defaultAuthority = "ROLE_USER";
    final int defaultEnabed = 1;
    final int defaultMeetingId = 0;

    public UserServiceImpl(UserRepository userRepository, LoggedUserRepository loggedUserRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.loggedUserRepository = loggedUserRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> findAll() {

        return userRepository.findAll();
       // return userRepository.findAllByOrderByLastNameAsc(); TODO do zbadania później tej możliwości przy mojej db
    }


    @Override
    @Transactional
    public void save(MeetingAppUser meetingAppUser) {

        User user = createUserToSave(meetingAppUser);
        Role role = createRoleToSave(meetingAppUser);
        LoggedInUser loggedInUser = createLoggedInUserToSave(meetingAppUser, user, role);

        loggedUserRepository.save(loggedInUser);

    }


    private Role createRoleToSave(MeetingAppUser meetingAppUser) {
        Role role = new Role();
        role.setUsername(meetingAppUser.getUserName());
        role.setAuthority(defaultAuthority);
        return role;
    }

    private LoggedInUser createLoggedInUserToSave(MeetingAppUser meetingAppUser, User user, Role role) {
        LoggedInUser loggedInUser = new LoggedInUser();
        loggedInUser.setUsername(meetingAppUser.getUserName());
        loggedInUser.setPassword(passwordEncoder.encode(meetingAppUser.getPassword()));
        loggedInUser.setEnabled(defaultEnabed);
        loggedInUser.setUser(user);
        loggedInUser.setRole(role);
        return loggedInUser;
    }

    private User createUserToSave(MeetingAppUser meetingAppUser) {
        User user = new User();
        user.setEmail(meetingAppUser.getEmail());
        user.setUsername(meetingAppUser.getUserName());
        user.setFieldOfExpertise(meetingAppUser.getFieldOfExpertise());
        user.setAirportCode(meetingAppUser.getAirportCode());
        user.setDate(meetingAppUser.getDate());
        user.setTimeFrom(meetingAppUser.getTimeFrom());
        user.setTimeUntil(meetingAppUser.getTimeUntil());
        user.setMeetingsId(defaultMeetingId);
        return user;
    }

    @Override
    @Transactional
    public void save(User user){
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void save(LoggedInUser loggedInUser) {
        loggedUserRepository.save(loggedInUser);
    }

    @Override
    @Transactional
    public void delete(LoggedInUser loggedInUser) {
        loggedUserRepository.delete(loggedInUser);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public LoggedInUser findLoggedInUserByUsername (String username){
        return loggedUserRepository.findByUsername(username);
    }
}
