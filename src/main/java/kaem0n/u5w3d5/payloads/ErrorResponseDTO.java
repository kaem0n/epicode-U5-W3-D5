package kaem0n.u5w3d5.payloads;

import java.time.LocalDateTime;

public record ErrorResponseDTO(String msg, LocalDateTime timestamp) {
}
