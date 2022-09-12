package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private static Long index = 0L;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private void setId() {
        id = ++index;
    }

}
