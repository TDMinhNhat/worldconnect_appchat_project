package dev.skyherobrine.project.backend.services;

import dev.skyherobrine.project.backend.dtos.UserDTO;
import dev.skyherobrine.project.backend.models.mariadb.User;
import dev.skyherobrine.project.backend.models.mariadb.UserRole;
import dev.skyherobrine.project.backend.repositories.mariadb.UserRepository;
import dev.skyherobrine.project.backend.repositories.mariadb.UserRoleRepository;
import dev.skyherobrine.project.backend.utils.EncodeDecodeUtil;
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
        log.info("User Service: call the service add user");
        UserRole userRole = userRoleRepository.findById(request.getRoleId()).orElseThrow(() -> new EntityNotFoundException("The role with id " + request.getRoleId() + " wasn't found"));

        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getSex(),
                request.getPhone(),
                request.getAddress(),
                EncodeDecodeUtil.encode(request.getEmail()),
                EncodeDecodeUtil.encode(request.getUsername()),
                EncodeDecodeUtil.encode(request.getPassword()),
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
        user.setEmail(EncodeDecodeUtil.encode(request.getEmail()));
        user.setUsername(EncodeDecodeUtil.encode(request.getUsername()));
        user.setPassword(EncodeDecodeUtil.encode(request.getPassword()));
        user.setRole(userRole);

        return userRepository.save(user);
    }
}
