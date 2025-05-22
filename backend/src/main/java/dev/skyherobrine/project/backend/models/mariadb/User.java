package dev.skyherobrine.project.backend.models.mariadb;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.skyherobrine.project.backend.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Users")
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100) @NonNull
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100) @NonNull
    private String lastName;

    @Column(nullable = false) @NonNull
    private Boolean sex;

    @Column(nullable = false, unique = true, length = 30) @NonNull
    private String phone;

    @Column(length = 500)
    private String address;

    @Column(nullable = false, unique = true, length = 200) @NonNull
    private String email;

    @Column(nullable = false, unique = true, length = 100) @NonNull
    private String username;

    @Column(nullable = false, length = 100) @NonNull
    private String password;

    @ManyToOne @JoinColumn(name = "role_id", nullable = false) @NonNull
    private UserRole role;

    @Column(name = "account_verified", nullable = false)
    private boolean accountVerified;

    @Column(name = "date_created", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @Column(name = "date_updated", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateUpdated;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private UserStatus status;

    public User(@NonNull String firstName, @NonNull String lastName, @NonNull Boolean sex, @NonNull String phone, String address, @NonNull String email, @NonNull String username, @NonNull String password, @NonNull UserRole role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.accountVerified = false;
    }

    @PrePersist
    public void prePersist() {
        dateCreated = dateUpdated = LocalDateTime.now();
        status = UserStatus.ACTIVE;
    }
}
