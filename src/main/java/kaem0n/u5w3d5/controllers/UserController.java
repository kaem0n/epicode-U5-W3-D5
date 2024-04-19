package kaem0n.u5w3d5.controllers;

import kaem0n.u5w3d5.entities.User;
import kaem0n.u5w3d5.exceptions.BadRequestException;
import kaem0n.u5w3d5.payloads.UserDTO;
import kaem0n.u5w3d5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService us;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String sort) {
        return us.findAll(page, size, sort);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User getUserById(@PathVariable long id) {
        return us.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(@PathVariable long id) {
        us.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User updateUser(@PathVariable long id, @RequestBody @Validated UserDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        else return us.update(id, payload);
    }

    @GetMapping("/me")
    public User getMyProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        return currentAuthenticatedUser;
    }

    @DeleteMapping("/me")
    public void deleteMyProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        us.delete(currentAuthenticatedUser.getId());
    }

    @PutMapping("/me")
    public User updateMyProfile(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody @Validated UserDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        else return us.update(currentAuthenticatedUser.getId(), payload);
    }
}
