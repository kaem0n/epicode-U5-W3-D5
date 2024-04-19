package kaem0n.u5w3d5.entities;

import kaem0n.u5w3d5.enums.UserRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    @Setter(AccessLevel.NONE)
    private long id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private UserRole role;

    public User(String username, String password, String fullName, String email, UserRole role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }
}
