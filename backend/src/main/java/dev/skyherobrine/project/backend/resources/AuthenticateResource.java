package dev.skyherobrine.project.backend.resources;

import dev.skyherobrine.project.backend.dtos.UserDTO;
import dev.skyherobrine.project.backend.models.Response;
import dev.skyherobrine.project.backend.models.mariadb.User;
import dev.skyherobrine.project.backend.projects.UserProject;
import dev.skyherobrine.project.backend.repositories.mariadb.UserRepository;
import dev.skyherobrine.project.backend.services.UserService;
import dev.skyherobrine.project.backend.utils.EncodeDecodeUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        User user = userRepository.findByEmailOrUsernameAndPassword(
                EncodeDecodeUtil.encode(account),
                EncodeDecodeUtil.encode(account),
                EncodeDecodeUtil.encode(password)
        ).orElseThrow(() -> new EntityNotFoundException("The email or username and password isn't corrected"));
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Login account successfully",
                new UserProject(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getSex(),
                        user.getPhone(),
                        user.getAddress(),
                        EncodeDecodeUtil.decode(user.getEmail()),
                        EncodeDecodeUtil.decode(user.getUsername()),
                        user.getRole(),
                        user.isAccountVerified(),
                        user.getDateCreated(),
                        user.getDateUpdated(),
                        user.getStatus()
                )
        ));
    }

    @GetMapping("/searching_user")
    public ResponseEntity<Response> searchingUser(@RequestParam String content) {
        log.info("Authenticate: call the api searching user");
        List<UserProject> user = userRepository.getSearchingUser(
                content,
                EncodeDecodeUtil.encode(content),
                EncodeDecodeUtil.encode(content)
        ).stream().map(item -> new UserProject(
                item.getId(),
                item.getFirstName(),
                item.getLastName(),
                item.getSex(),
                item.getPhone(),
                item.getAddress(),
                EncodeDecodeUtil.decode(item.getEmail()),
                EncodeDecodeUtil.decode(item.getUsername()),
                item.getRole(),
                item.isAccountVerified(),
                item.getDateCreated(),
                item.getDateUpdated(),
                item.getStatus()))
                .toList();
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Searching user successfully",
                user
        ));
    }
}
