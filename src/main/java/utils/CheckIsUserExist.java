package utils;

import model.User;
import org.apache.log4j.Logger;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Optional;


/**
 * @author Sergey Klunniy
 */
public class CheckIsUserExist {

    private static final Logger logger = Logger.getLogger(CheckIsUserExist.class);
    private static final UserService userService = new UserServiceImpl();

    public static boolean isUserExist(String email, String password, HttpSession session) {

        Optional<User> optUser = userService.findUserByEmail(email);

        if (optUser.isPresent() && optUser.get().isAvailable()) {
            User user = optUser.get();
            String roleFromDb = user.getRole().toString().toLowerCase();
            String saltFromDb = user.getSalt();
            String hashPasswordFromDb = user.getHashPassword();

            String createHashPassword = HashUtils.getSHA256SecurePassword(password, saltFromDb);
            if (Objects.equals(hashPasswordFromDb, createHashPassword) & Objects.equals(email, user.getEmail())) {
                        session.setAttribute("user", user);
                        session.setAttribute("role", roleFromDb);
                logger.info("isUserExist(): true, add in session 'user' and 'role'" );
                return true;
            } else {
                logger.error("isUserExist(): false");
                return false;
            }
        } else {
            logger.error("isUserExist(): optUser not present, return false");
            return false;
        }
    }
}
