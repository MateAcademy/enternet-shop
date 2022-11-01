package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.Role;

import java.util.Objects;

/**
 * @author Sergey Klunniy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String email;
    private String password;
    private Role role;
    private boolean available;

//    private static Long index = 0L;

 //   private Long setId() {
//        return ++index;
//    }

    public User(String email, String password) {
//        this.id = setId();
        this.email = email;
        this.password = password;
    }

    public User(Long id, String email, String password, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String email, String password, Role role, boolean available) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.available = available;
    }

    public User(String email, String password, Role role) {
//        this.id = setId();
        this.email = email;
        this.password = password;
        this.role = role;
    }

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
