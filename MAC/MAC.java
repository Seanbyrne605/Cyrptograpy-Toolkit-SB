import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class MAC {

    public static void main(String[] args) {
        try {
            System.out.println("Please enter you key:");

            Scanner scr = new Scanner(System.in);

            String secretKey = scr.nextLine();
            
            System.out.println("Please enter you input string:");
            String message = scr.nextLine();

            scr.close();
            // Choose the algorithm (e.g., HMAC-SHA-256)
            String algorithm = "HmacSHA256";

            // Generate a secret key for the HMAC algorithm
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), algorithm);

            // Create the instance of the HMAC
            Mac mac = Mac.getInstance(algorithm);
            mac.init(secretKeySpec);

            // Compute the MAC
            byte[] macBytes = mac.doFinal(message.getBytes());

            // Convert the result to a Base64-encoded string for better readability
            String macString = Base64.getEncoder().encodeToString(macBytes);

            System.out.println("Original Message: " + message);
            System.out.println("MAC: " + macString);

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    
}
