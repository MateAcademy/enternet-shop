package utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.soap.encoding.Hex;

/**
 * @author Sergey Klunniy
 */
public class HashUtils {

    private static Logger logger = Logger.getLogger(HashUtils.class);

    public HashUtils() {
    }

    //encrypt password with SHA-256
    public static String getSHA256SecurePassword(String passwordToHash) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedhash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }


    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(getSHA256SecurePassword("hillel+--tt"));
    }


    //encrypt password with SHA-256 and SALT
    public static String getSHA256SecurePassword(String passwordToHash, String salt) {
        String generatePassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatePassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error("Can't find algorithm ", e);
        }
        return generatePassword;
    }

    public static String getRandomSalt() {
        byte[] array = new byte[6];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
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


    public static void myDecryptToCCMTAFileEmail() throws SecurityException {

        try {
            /**
             * повертаэмо вміст ключа:
             */
            File publicKeyFile = new File("certificate.pem");
            byte[] publicKeyBytes = Files.readAllBytes(publicKeyFile.toPath());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            PrivateKey privateKey = keyFactory.generatePrivate(publicKeySpec);

            /**
             * Дешифрування файлу
             */
            Path tempFile = Files.createTempFile("temp", "txt");
            File file = new File("smime.p7m");
            byte[] encryptedFileBytes = Files.readAllBytes(file.toPath());
            Cipher decryptCipher = Cipher.getInstance("RSA");
            decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedFileBytes = decryptCipher.doFinal(encryptedFileBytes);
            try (FileOutputStream stream = new FileOutputStream(tempFile.toFile())) {
                stream.write(decryptedFileBytes);
            }
        } catch (NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException |
                 NoSuchPaddingException | IOException | InvalidKeySpecException var3) {
            throw new SecurityException(var3);
        }
    }

//    public static void main(String[] args) {
//        myDecryptToCCMTAFileEmail();
//    }

}
