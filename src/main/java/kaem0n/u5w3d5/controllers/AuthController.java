package kaem0n.u5w3d5.controllers;

import kaem0n.u5w3d5.entities.User;
import kaem0n.u5w3d5.exceptions.BadRequestException;
import kaem0n.u5w3d5.payloads.LoginDTO;
import kaem0n.u5w3d5.payloads.LoginResponseDTO;
import kaem0n.u5w3d5.payloads.UserDTO;
import kaem0n.u5w3d5.services.AuthService;
import kaem0n.u5w3d5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService as;
    @Autowired
    private UserService us;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO payload) {
        return as.login(payload);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    private User saveNewUser(@RequestBody @Validated UserDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        else return us.save(payload);
    }
}
