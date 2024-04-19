package kaem0n.u5w3d5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserDTO(@NotEmpty(message = "Username field must not be empty.")
                      @Size(min = 3, max = 20, message = "Username field length must be between 3 and 20 characters.")
                      String username,
                      @NotEmpty(message = "Password field must not be empty.")
                      @Size(min = 8, message = "Password must be at least 8 characters long.")
                      String password,
                      @NotEmpty(message = "Full name field must not be empty.")
                      @Size(min = 3, max = 20, message = "Full name field length must be between 3 and 20 characters.")
                      String fullName,
                      @NotEmpty(message = "Email field must not be empty.")
                      @Email(message = "Invalid email format.")
                      String email,
                      @NotEmpty(message = "Role field must not be empty.")
                      @Pattern(regexp = "USER|EVENT_ORGANIZER", message = "Invalid role value (available: USER, EVENT_ORGANIZER")
                      String role) {
}
