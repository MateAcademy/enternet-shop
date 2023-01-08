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
public class  Code {
    private Long id_code;
    private String value;
    private User user;

    public Code(String value, User user) {
        this.value = value;
        this.user = user;
    }

}
