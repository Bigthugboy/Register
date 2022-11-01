package semicolon.africa.login.dto.request;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
public class AppUserRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
