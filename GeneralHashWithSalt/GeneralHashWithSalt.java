import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;
public class GeneralHashWithSalt {

    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        System.out.println("Please enter your input string:");
        String input = scr.nextLine();

        scr.close();

        String salt = generateSalt();
        String hashedPassword = hashPassword(input, salt);

        System.out.println("Original String: " + input);
        System.out.println("Salt: " + salt);
        System.out.println("Hashed String: " + hashedPassword);
    }

    private static String generateSalt() {
        // Generate a random salt using SecureRandom
        byte[] salt = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private static String hashPassword(String inputString, String salt) {
        try {
            // Combine the password and salt and then hash
            String saltedPassword = inputString + salt;
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = messageDigest.digest(saltedPassword.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                hexStringBuilder.append(String.format("%02x", b));
            }

            return hexStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

      private static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }
}
