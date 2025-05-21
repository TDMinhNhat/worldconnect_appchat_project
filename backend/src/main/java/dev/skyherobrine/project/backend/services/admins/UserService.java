package dev.skyherobrine.project.backend.services.admins;

import dev.skyherobrine.project.backend.dtos.UserDTO;
import dev.skyherobrine.project.backend.models.mariadb.User;
import dev.skyherobrine.project.backend.models.mariadb.UserRole;
import dev.skyherobrine.project.backend.repositories.mariadb.UserRepository;
import dev.skyherobrine.project.backend.repositories.mariadb.UserRoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public User addUser(UserDTO request) {
        log.info("Admin User Service: call the service add user");
        UserRole userRole = userRoleRepository.findById(request.getRoleId()).orElseThrow(() -> new EntityNotFoundException("The role with id " + request.getRoleId() + " wasn't found"));

        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getSex(),
                request.getPhone(),
                request.getAddress(),
                request.getEmail(),
                request.getUsername(),
                request.getPassword(),
                userRole
        );

        return userRepository.save(user);
    }

    public User updateUser(Long id, UserDTO request) {
        log.info("Admin User Service: call the service update user");
        UserRole userRole = userRoleRepository.findById(request.getRoleId()).orElseThrow(() -> new EntityNotFoundException("The role with id " + request.getRoleId() + " wasn't found"));

        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The user with id " + id + " wasn't found"));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setSex(request.getSex());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(userRole);

        return userRepository.save(user);
    }
}
