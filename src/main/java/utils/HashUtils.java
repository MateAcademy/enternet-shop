package utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.apache.soap.encoding.Hex;

/**
 * @author Sergey Klunniy
 */
public class HashUtils {
    public HashUtils() {
    }

    public static String hmac(String data, String key, HashUtils.Algorithm algorithm) throws SecurityException {
        try {
            Mac mac = Mac.getInstance(algorithm.name());
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm.name());
            mac.init(secret_key);
            return Hex.encode(mac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (InvalidKeyException | NoSuchAlgorithmException var5) {
            throw new SecurityException(var5);
        }
    }

    public static String encrypt(String text, String key) throws SecurityException {
        try {
            Key aesKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(1, aesKey);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            return Hex.encode(encrypted);
        } catch (NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException |
                 NoSuchPaddingException var5) {
            throw new SecurityException(var5);
        }
    }

    public static String decrypt(String hexText, String key) throws SecurityException {
        try {
            Key aesKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, aesKey);
            byte[] toDecode = Hex.decode(hexText);
            return new String(cipher.doFinal(toDecode));
        } catch (NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException |
                 NoSuchPaddingException var5) {
            throw new SecurityException(var5);
        }
    }

    public static String encrypt(String text, Key key) throws SecurityException {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(1, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(text)), StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException |
                 NoSuchPaddingException var3) {
            throw new SecurityException(var3);
        }
    }

    public static String decrypt(String text, Key key) throws SecurityException {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(2, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(text)), StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException |
                 NoSuchPaddingException var3) {
            throw new SecurityException(var3);
        }
    }

    public static enum Algorithm {
        HmacMD5,
        HmacSHA256,
        HmacSHA512;

        private Algorithm() {
        }
    }
}
