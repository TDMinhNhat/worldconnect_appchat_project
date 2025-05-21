package dev.skyherobrine.project.backend.resources.admins.impl;

import dev.skyherobrine.project.backend.dtos.UserDTO;
import dev.skyherobrine.project.backend.enums.UserStatus;
import dev.skyherobrine.project.backend.exceptions.RequestParamException;
import dev.skyherobrine.project.backend.models.Response;
import dev.skyherobrine.project.backend.models.mariadb.User;
import dev.skyherobrine.project.backend.repositories.mariadb.UserRepository;
import dev.skyherobrine.project.backend.resources.admins.IManagementResource;
import dev.skyherobrine.project.backend.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/user")
@Slf4j
public class UserResource implements IManagementResource<UserDTO, Long> {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserResource(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping
    @Override
    public ResponseEntity<Response> getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        log.info("Admin User: call the api get all users");
        if(size == null)  throw new RequestParamException("The size parameter is required");
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Get all the users by page and size",
                userRepository.findAll(Pageable.ofSize(size == null ? null : size).withPage(page == null ? 0 : page))
        ));
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Response> getById(@PathVariable Long id) {
        log.info("Admin User: call the api get user by id");
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Get the user by id",
                userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The user with id " + id + " wasn't found"))
        ));
    }

    @PostMapping
    @Override
    public ResponseEntity<Response> add(@Valid @RequestBody UserDTO request) {
        log.info("Admin User: call the api add user");
        User result = userService.addUser(request);
        log.info("Admin User: user added successfully");
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Add user successfully",
                result
        ));
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Response> update(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        log.info("Admin User: call the api update user information");
        User user = userService.updateUser(id, userDTO);
        log.info("Admin User: user updated successfully");
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Update user successfully",
                user
        ));
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Response> delete(@PathVariable("id") Long id) {
        log.info("Admin User: call the api delete user");
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The user with id " + id + " wasn't found"));
        user.setStatus(UserStatus.DELETED);
        User result = userRepository.save(user);
        log.info("Admin User: user deleted successfully");
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Delete user successfully",
                result
        ));
    }
}
