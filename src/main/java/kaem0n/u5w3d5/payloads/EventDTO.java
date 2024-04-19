package kaem0n.u5w3d5.payloads;

import jakarta.validation.constraints.Pattern;

public record EventDTO(String title,
                       String description,
                       String place,
                       @Pattern(regexp = "/[1-9][0-9][0-9]{2}-([0][1-9]|[1][0-2])-([1-2][0-9]|[0][1-9]|[3][0-1])/gm", message = "Date must follow YYYY-MM-DD format.")
                       String date,
                       int maxCapacity,
                       long organizerId) {
}
