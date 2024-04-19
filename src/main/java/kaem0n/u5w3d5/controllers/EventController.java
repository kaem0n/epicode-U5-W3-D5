package kaem0n.u5w3d5.controllers;

import kaem0n.u5w3d5.entities.Event;
import kaem0n.u5w3d5.exceptions.BadRequestException;
import kaem0n.u5w3d5.payloads.EventDTO;
import kaem0n.u5w3d5.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService es;

    @GetMapping
    private Page<Event> getAllEvents(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String sort) {
        return es.findAll(page, size, sort);
    }

    @GetMapping("/{id}")
    private Event getEventById(@PathVariable long id) {
        return es.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteEvent(@PathVariable long id) {
        es.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Event saveNewEvent(@RequestBody @Validated EventDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        else return es.save(payload);
    }

    @PutMapping("/{id}")
    private Event updateEvent(@PathVariable long id, @RequestBody @Validated EventDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        else return es.update(id, payload);
    }
}
