package kaem0n.u5w3d5.services;

import kaem0n.u5w3d5.entities.Event;
import kaem0n.u5w3d5.exceptions.NotFoundException;
import kaem0n.u5w3d5.payloads.EventDTO;
import kaem0n.u5w3d5.repositories.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EventService {
    @Autowired
    private EventDAO ed;
    @Autowired UserService us;

    public Page<Event> findAll(int page, int size, String sort) {
        if (size > 50) size = 50;
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return ed.findAll(p);
    }

    public Event findById(long id) {
        return ed.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Event save(EventDTO payload) {
        Event newEvent = new Event(payload.title(), payload.description(), payload.place(), LocalDate.parse(payload.date()), payload.maxCapacity(), us.findById(payload.organizerId()));
        return ed.save(newEvent);
    }

    public void delete(long id) {
        Event found = this.findById(id);
        ed.delete(found);
    }

    public Event update(long id, EventDTO payload) {
        Event found = this.findById(id);
        found.setTitle(payload.title());
        found.setDescription(payload.description());
        found.setPlace(payload.place());
        found.setDate(LocalDate.parse(payload.date()));
        found.setMaxCapacity(payload.maxCapacity());
        return ed.save(found);
    }
}
