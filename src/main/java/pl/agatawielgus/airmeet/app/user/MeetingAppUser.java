package pl.agatawielgus.airmeet.app.user;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.agatawielgus.airmeet.app.validation.FieldMatch;
import pl.agatawielgus.airmeet.app.validation.ValidEmail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
@FieldDefaults(level=AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
public class MeetingAppUser {

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    String userName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    String password;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    String matchingPassword;

    @ValidEmail
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    String email;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    String fieldOfExpertise;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    String airportCode;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    String date;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    String timeFrom;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    String timeUntil;







}
