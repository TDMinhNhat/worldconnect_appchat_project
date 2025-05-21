package dev.skyherobrine.project.backend.resources;

import dev.skyherobrine.project.backend.dtos.UserDTO;
import dev.skyherobrine.project.backend.models.Response;
import dev.skyherobrine.project.backend.models.mariadb.User;
import dev.skyherobrine.project.backend.repositories.mariadb.UserRepository;
import dev.skyherobrine.project.backend.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authenticate")
@Slf4j
public class AuthenticateResource {

    private final UserRepository userRepository;
    private final UserService userService;

    public AuthenticateResource(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Response> registerAccount(@Valid @RequestBody UserDTO request) {
        log.info("Authenticate: call the api register account");
        User user = userService.addUser(request);
        log.info("Authenticate: register account successfully");
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Register account successfully",
                user
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<Response> loginAccount(
            @RequestParam String account,
            @RequestParam String password
    ) {
        log.info("Authenticate: call the api login account");
        User user = userRepository.findByEmailOrUsernameAndPassword(account, account, password).orElseThrow(() -> new EntityNotFoundException("The email or username and password isn't corrected"));
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Login account successfully",
                user
        ));
    }
}
