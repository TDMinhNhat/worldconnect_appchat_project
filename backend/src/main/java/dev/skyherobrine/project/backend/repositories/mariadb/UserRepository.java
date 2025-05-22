package dev.skyherobrine.project.backend.repositories.mariadb;

import dev.skyherobrine.project.backend.models.mariadb.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailOrUsernameAndPassword(String email, String username, String password);

    @Query("""
            SELECT u FROM User u
            WHERE u.username LIKE %:username% OR u.email LIKE %:email% OR u.firstName LIKE %:input% OR u.lastName LIKE %:input% OR u.phone LIKE %:input% 
            ORDER BY u.id
            """)
    List<User> getSearchingUser(@Param("input") String input, @Param("username") String username, @Param("email") String email);
}
