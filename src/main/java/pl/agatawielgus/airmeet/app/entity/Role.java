package pl.agatawielgus.airmeet.app.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@FieldDefaults(level=AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    @Column(name = "username")
    String username;

    @Column(name = "authority")
    String authority;


}
