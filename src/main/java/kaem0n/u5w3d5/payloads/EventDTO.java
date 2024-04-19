package kaem0n.u5w3d5.payloads;

import jakarta.validation.constraints.*;

public record EventDTO(@NotEmpty(message = "Title field must not be empty.")
                       @Size(min = 3, max = 20, message = "Title field length must be between 3 and 20 characters.")
                       String title,
                       @NotEmpty(message = "Description field must not be empty.")
                       @Size(min = 3, max = 255, message = "Description field length must be between 3 and 20 characters.")
                       String description,
                       @NotEmpty(message = "Place field must not be empty.")
                       @Size(min = 3, max = 20, message = "Place field length must be between 3 and 20 characters.")
                       String place,
                       @NotEmpty(message = "You must specify the event date (YYYY-MM-DD).")
                       @Pattern(regexp = "/[1-9][0-9][0-9]{2}-([0][1-9]|[1][0-2])-([1-2][0-9]|[0][1-9]|[3][0-1])/gm", message = "Date must follow YYYY-MM-DD format.")
                       String date,
                       @Min(value = 100, message = "Minimum participants number: 100.")
                       @Max(value = 10000, message = "Maximum participants number: 10000.")
                       @NotNull(message = "Capacity field must not be null.")
                       int maxCapacity,
                       @Min(value = 1)
                       @NotNull(message = "Organizer ID field must not be null.")
                       long organizerId) {
}
