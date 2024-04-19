package kaem0n.u5w3d5.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record TicketDTO(@Min(value = 1)
                        @NotNull(message = "User ID field must not be null.")
                        long userId,
                        @Min(value = 1)
                        @NotNull(message = "Event ID field must not be null.")
                        long eventId) {
}
