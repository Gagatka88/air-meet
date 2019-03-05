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
@FieldDefaults(level=AccessLevel.PRIVATE)
@Entity
@Table(name="user_data")
public class User {


    @Column(name = "email")
    String email;

    @Id
    @Column(name = "username")
    String username;

    @Column(name = "field_of_expertise")
    String fieldOfExpertise;

    @Column(name = "airport_name")
    String airportCode;

    @Column(name = "date")
    String date;

    @Column(name = "from_time")
    String timeFrom;

    @Column(name = "until_time")
    String timeUntil;

    @Column(name = "meetings_id")
    int meetingsId;

    public User(String email, String username, String fieldOfExpertise, String airportCode, String date, String timeFrom, String timeUntil, int meetingsId) {

        this.email = email;
        this.username = username;
        this.fieldOfExpertise = fieldOfExpertise;
        this.airportCode = airportCode;
        this.date = date;
        this.timeFrom = timeFrom;
        this.timeUntil = timeUntil;
        this.meetingsId = meetingsId;
    }

    public User() {
    }
}
