package kaem0n.u5w3d5.controllers;

import kaem0n.u5w3d5.entities.User;
import kaem0n.u5w3d5.exceptions.BadRequestException;
import kaem0n.u5w3d5.payloads.UserDTO;
import kaem0n.u5w3d5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService us;

    @GetMapping
    private Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String sort) {
        return us.findAll(page, size, sort);
    }

    @GetMapping("/{id}")
    private User getUserById(@PathVariable long id) {
        return us.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteUser(@PathVariable long id) {
        us.delete(id);
    }

    @PutMapping("/{id}")
    private User updateUser(@PathVariable long id, @RequestBody @Validated UserDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        else return us.update(id, payload);
    }
}
