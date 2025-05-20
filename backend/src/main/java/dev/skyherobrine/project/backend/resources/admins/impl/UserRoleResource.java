package dev.skyherobrine.project.backend.resources.admins.impl;

import dev.skyherobrine.project.backend.dtos.UserRoleDTO;
import dev.skyherobrine.project.backend.models.Response;
import dev.skyherobrine.project.backend.models.mariadb.UserRole;
import dev.skyherobrine.project.backend.repositories.mariadb.UserRoleRepository;
import dev.skyherobrine.project.backend.resources.admins.IManagementResource;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/user_role")
@Slf4j
public class UserRoleResource implements IManagementResource<UserRoleDTO,Long> {

    private final UserRoleRepository userRoleRepository;

    public UserRoleResource(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @GetMapping
    @Override
    public ResponseEntity<Response> getAll(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size)
    {
        log.info("Admin User Role: call the api get all user roles");
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Get all the user roles",
                userRoleRepository.findAll()
        ));
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Response> getById(@PathVariable("id") Long id) {
        log.info("Admin User Role: call the api get user role by id");
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Get user role by id",
                userRoleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The user role wasn't found by id = " + id))
        ));
    }

    @PostMapping
    @Override
    public ResponseEntity<Response> add(@Valid @RequestBody UserRoleDTO request) {
        log.info("Admin User Role: call the api add user role");
        UserRole userRole = new UserRole(request.getRoleName());
        UserRole target = userRoleRepository.save(userRole);
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Add user role successfully",
                target
        ));
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Response> update(@PathVariable("id") Long id, @Valid @RequestBody UserRoleDTO request) {
        log.info("Admin User Role: call the api update user role");
        UserRole userRole = userRoleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The user role wasn't found by id = " + id));
        userRole.setRoleName(request.getRoleName());
        UserRole target = userRoleRepository.save(userRole);
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Update user role successfully",
                target
        ));
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Response> delete(@PathVariable("id") Long id) {
        log.info("Admin User Role: call the api delete user role");
        UserRole userRole = userRoleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The user role wasn't found by id = " + id));
        userRole.setStatus(false);
        UserRole target = userRoleRepository.save(userRole);
        return ResponseEntity.ok(new Response(
                HttpStatus.OK.value(),
                "Delete user role successfully",
                target
        ));
    }
}
