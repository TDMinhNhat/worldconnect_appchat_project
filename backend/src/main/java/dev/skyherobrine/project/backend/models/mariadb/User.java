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
    @NotNull(message = "The first name can't be null")
    @NotBlank(message = "The first name can't be empty")
    @Size(max = 100, message = "The first name can't be longer than 100 characters")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100) @NonNull
    @NotNull(message = "The last name can't be null")
    @NotBlank(message = "The last name can't be empty")
    @Size(max = 100, message = "The last name can't be longer than 100 characters")
    private String lastName;

    @Column(nullable = false) @NonNull
    @NotNull(message = "The sex can't be null")
    @NotBlank(message = "The sex can't be empty")
    private Boolean sex;

    @Column(nullable = false, unique = true, length = 30) @NonNull
    @NotNull(message = "The phone can't be null")
    @NotBlank(message = "The phone can't be empty")
    @Size(max = 30, min = 10, message = "The phone can't be longer than 30 digits and shorter than 10 digits")
    private String phone;

    @Column(length = 500)
    private String address;

    @Column(nullable = false, unique = true, length = 200) @NonNull
    @NotNull(message = "The email can't be null")
    @NotBlank(message = "The email can't be empty")
    @Email(message = "The email isn't valid", regexp = ".+@.+\\..+")
    private String email;

    @Column(nullable = false, unique = true, length = 100) @NonNull
    @NotNull(message = "The username can't be null")
    @NotBlank(message = "The username can't be empty")
    private String username;

    @Column(nullable = false, length = 100) @NonNull
    @NotNull(message = "The password can't be null")
    @NotBlank(message = "The password can't be empty")
    private String password;

    @ManyToOne @JoinColumn(name = "role_id", nullable = false) @NonNull
    @NotNull(message = "The role can't be null")
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
