package kaem0n.u5w3d5.controllers;

import kaem0n.u5w3d5.entities.Ticket;
import kaem0n.u5w3d5.exceptions.BadRequestException;
import kaem0n.u5w3d5.payloads.TicketDTO;
import kaem0n.u5w3d5.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ts;

    @GetMapping("/{id}")
    private Ticket getTicketById(@PathVariable long id) {
        return ts.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteTicket(@PathVariable long id) {
        ts.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Ticket saveNewTicket(@RequestBody @Validated TicketDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        else return ts.save(payload);
    }
}
