package kaem0n.u5w3d5.services;

import kaem0n.u5w3d5.entities.Event;
import kaem0n.u5w3d5.entities.Ticket;
import kaem0n.u5w3d5.exceptions.BadRequestException;
import kaem0n.u5w3d5.exceptions.NotFoundException;
import kaem0n.u5w3d5.payloads.TicketDTO;
import kaem0n.u5w3d5.repositories.TicketDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketDAO td;
    @Autowired
    private UserService us;
    @Autowired
    private EventService es;

    public Ticket findById(long id) {
        return td.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Ticket save(TicketDTO payload) {
        Event event = es.findById(payload.eventId());
        if (event.getTickets().size() < event.getMaxCapacity()) {
            Ticket newTicket = new Ticket(us.findById(payload.userId()), event);
            return td.save(newTicket);
        } else throw new BadRequestException("Event is full.");
    }

    public void delete(long id) {
        Ticket found = this.findById(id);
        td.delete(found);
    }
}
