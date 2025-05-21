package dev.skyherobrine.project.backend.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UserDTO {

    @Column(name = "first_name", nullable = false, length = 100)
    @NotNull(message = "The first name can't be null")
    @NotBlank(message = "The first name can't be empty")
    @Size(max = 100, message = "The first name can't be longer than 100 characters")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    @NotNull(message = "The last name can't be null")
    @NotBlank(message = "The last name can't be empty")
    @Size(max = 100, message = "The last name can't be longer than 100 characters")
    private String lastName;

    @Column(nullable = false)
    @NotNull(message = "The sex can't be null")
    @NotBlank(message = "The sex can't be empty")
    private Boolean sex;

    @Column(nullable = false, unique = true, length = 30)
    @NotNull(message = "The phone can't be null")
    @NotBlank(message = "The phone can't be empty")
    @Size(max = 30, min = 10, message = "The phone can't be longer than 30 digits and shorter than 10 digits")
    private String phone;

    @Column(length = 500)
    private String address;

    @Column(nullable = false, unique = true, length = 200)
    @NotNull(message = "The email can't be null")
    @NotBlank(message = "The email can't be empty")
    @Email(message = "The email isn't valid", regexp = ".+@.+\\..+")
    private String email;

    @Column(nullable = false, unique = true, length = 100)
    @NotNull(message = "The username can't be null")
    @NotBlank(message = "The username can't be empty")
    private String username;

    @Column(nullable = false, length = 100)
    @NotNull(message = "The password can't be null")
    @NotBlank(message = "The password can't be empty")
    private String password;

    @NotNull(message = "The role can't be null")
    @NotBlank(message = "The role can't be empty")
    private Long roleId;
}
