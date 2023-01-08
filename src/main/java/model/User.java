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

    //    private static Long index = 0L;

 //   private Long setId() {
//        return ++index;
//    }

    public User(String name, String email, String password, String salt, String hashPassword, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.hashPassword = hashPassword;
        this.role = role;
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
//        this.id = setId();
        this.email = email;
        this.password = password;
    }
//
    public User( String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
//
//    public User(String email, String password, Role role, boolean available) {
//        this.email = email;
//        this.password = password;
//        this.role = role;
//        this.available = available;
//    }
//
//    public User(String email, String password, Role role) {
////        this.id = setId();
//        this.email = email;
//        this.password = password;
//        this.role = role;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, role);
    }
}
