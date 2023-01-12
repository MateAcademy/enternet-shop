package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.HashUtils;
import utils.Role;

import java.util.Objects;

/**
 * @author Sergey Klunniy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table
public class User {

//    @Id
//    @Column(name = "id")
    private Long id_user;

//    @Column(name = "name")
    private String name;

//    @Column(name = "email")
    private String email;

//    @Column(name = "password")
    private String password;

//    @Column(name = "salt")
    private String salt;

    private String hashPassword;
    private Role role;

    private boolean available = true;

    public boolean isAvailable() {
        return available;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        salt = HashUtils.getRandomSalt();;
        this.hashPassword = HashUtils.getSHA256SecurePassword(password, salt);;
        this.role = Role.USER;
    }

    public User(String name, String email, String password, Role role, Boolean available) {
        this.salt = HashUtils.getRandomSalt();
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.available = available;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return available == user.available && Objects.equals(id_user, user.id_user) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(salt, user.salt) && Objects.equals(hashPassword, user.hashPassword) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_user, name, email, password, salt, hashPassword, role, available);
    }
}
