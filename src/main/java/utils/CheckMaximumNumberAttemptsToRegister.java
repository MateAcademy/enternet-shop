package utils;

import javax.servlet.http.HttpServletRequest;

public class CheckMaximumNumberAttemptsToRegister {
    private int maxNumberLoginAttempts = 2;

    public boolean canIRegister(HttpServletRequest req) {
        Object countRegister = req.getAttribute("register");

        int countRegisterInt = 0;

        if (countRegister != null) {
            countRegisterInt = (int)countRegister;
        }

        if (countRegisterInt < maxNumberLoginAttempts) {
            req.setAttribute("register", ++countRegisterInt);
            return true;
        }
        return false;
    }
}
