package kaem0n.u5w3d5.services;

import kaem0n.u5w3d5.entities.User;
import kaem0n.u5w3d5.exceptions.UnauthorizedException;
import kaem0n.u5w3d5.payloads.LoginDTO;
import kaem0n.u5w3d5.payloads.LoginResponseDTO;
import kaem0n.u5w3d5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {
    @Autowired
    private UserService us;
    @Autowired
    private JWTTools tools;
    @Autowired
    private PasswordEncoder bcrypt;

    public LoginResponseDTO login(LoginDTO payload) {
        User found = us.findByUsername(payload.username());
        if (bcrypt.matches(payload.password(), found.getPassword())) return new LoginResponseDTO(tools.createToken(found));
        else throw new UnauthorizedException("Invalid credentials, try again.");
    }
}
