package kaem0n.u5w3d5.repositories;

import kaem0n.u5w3d5.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketDAO extends JpaRepository<Ticket, Long> {
}
