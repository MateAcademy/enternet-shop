package utils;

import java.util.Random;

public class RandomCodeToEmail {

    public static String get4DigitCode() {
        Random random = new Random();
        String randomCode = String.valueOf(random.nextInt(9999 -1000 + 1) + 1000);
        return randomCode;
    }
}
