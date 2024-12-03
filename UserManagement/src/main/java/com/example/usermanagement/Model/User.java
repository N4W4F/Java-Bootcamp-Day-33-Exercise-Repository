package com.example.usermanagement.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints =
        "LENGTH(name) >= 5 AND " +
                "LENGTH(username) >= 5 AND " +
                "LENGTH(password) >= 8 AND " +
                "age > 0 AND " +
                "(role = 'user' OR role = 'admin')"
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(16) not null")
    @NotEmpty(message = "User Name cannot be empty")
    @Size(min = 5, message = "User Name must be more than 4 characters")
    private String name;

    @Column(columnDefinition = "varchar(16) not null unique")
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 5, message = "Username must be more than 4 characters")
    private String username;

    @Column(columnDefinition = "varchar(21) not null")
    @NotEmpty(message = "User Password cannot be empty")
    private String password;

    @Column(columnDefinition = "varchar(32) not null unique")
    @NotEmpty(message = "User Email cannot be empty")
    @Email(message = "User Email must be in valid email format")
    private String email;

    @Column(columnDefinition = "varchar(10) not null")
    @NotEmpty(message = "User Role cannot be empty")
    @Pattern(regexp = "^(user|admin)$", message = "User must be either Admin or User")
    private String role;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "User Age cannot be empty")
    @Positive(message = "User Age must be valid integer")
    private Integer age;
}
