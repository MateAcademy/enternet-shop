package model;

/**
 * @author Sergey Klunniy
 */
public class Code {
    private String code;
    private User user;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Code(String code, User user) {
        this.code = code;
        this.user = user;
    }
}
