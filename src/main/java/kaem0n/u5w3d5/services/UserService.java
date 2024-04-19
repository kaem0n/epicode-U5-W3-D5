package kaem0n.u5w3d5.services;

import kaem0n.u5w3d5.entities.User;
import kaem0n.u5w3d5.enums.UserRole;
import kaem0n.u5w3d5.exceptions.BadRequestException;
import kaem0n.u5w3d5.exceptions.NotFoundException;
import kaem0n.u5w3d5.payloads.UserDTO;
import kaem0n.u5w3d5.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserDAO ud;

    public User findById(long id) {
        return ud.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public User save(UserDTO payload) {
        if (ud.existsByUsername(payload.username())) throw new BadRequestException("Username " + payload.username() + " is not available.");
        else if (ud.existsByEmail(payload.email())) throw new BadRequestException("Email " + payload.email() + " is already being used by another account.");
        else return new User(payload.username(), payload.password(), payload.fullName(), payload.email(), UserRole.valueOf(payload.role()));
    }

    public void delete(long id) {
        User found = this.findById(id);
        ud.delete(found);
    }

    public User update(long id, UserDTO payload) {
        User found = this.findById(id);
        if (!Objects.equals(found.getUsername(), payload.username()) && !ud.existsByUsername(payload.username())) found.setUsername(payload.username());
        else if (!Objects.equals(found.getUsername(), payload.username()) && ud.existsByUsername(payload.username())) throw new BadRequestException("Username " + payload.username() + " is not available.");
        found.setPassword(payload.password());
        found.setFullName(payload.fullName());
        if (!Objects.equals(found.getEmail(), payload.email()) && !ud.existsByEmail(payload.email())) found.setEmail(payload.email());
        else if (!Objects.equals(found.getEmail(), payload.email()) && ud.existsByEmail(payload.email())) throw new BadRequestException("Email " + payload.email() + " is already being used by another account.");
        found.setRole(UserRole.valueOf(payload.role()));
        return ud.save(found);
    }
}
