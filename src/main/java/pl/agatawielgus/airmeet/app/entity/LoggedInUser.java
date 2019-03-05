package pl.agatawielgus.airmeet.app.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name="users")
@FieldDefaults(level=AccessLevel.PRIVATE)
public class LoggedInUser {

    @Column(name="username")
    @Id
    String username;

    @Column(name="password")
    String password;

    @Column(name="enabled")
    int enabled;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="username")
    User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    Role role;

    public LoggedInUser() {
    }

    public LoggedInUser(String username, String password, int enabled, User user, Role role) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.user = user;
        this.role = role;
    }
}
