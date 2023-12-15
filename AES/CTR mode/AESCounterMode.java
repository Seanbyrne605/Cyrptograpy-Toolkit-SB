import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

public class AESCounterMode {

    public static void main(String[] args) throws Exception {
        Scanner scr = new Scanner(System.in);
        System.out.println("Please enter the key you will be using:");
        String keyHex = scr.nextLine();
        SecretKey secretKey = generateAESKeyFromHex(keyHex);

       // System.out.println("Please enter plaintext: ");
       // Scanner scr = new Scanner(System.in);
        
        String plaintext = "<XML>\n" +
        "  <CreditCardPurchase>\n" +
        "    <Merchant>Maynooth UNIV Inc</Merchant>\n" +
        "    <Buyer>Malice Ego</Buyer>\n" +
        "    <Date>01/10/2022</Date>\n" +
        "    <Amount>10000.00 Euro</Amount>\n" +
        "    <CCNumber>1010-9010-3412-4653</CCNumber>\n" +
        "  </CreditCardPurchase>\n" +
        "</XML>" ;

        //scr.close();

        // Convert plaintext to bytes
        byte[] plaintextBytes = plaintext.getBytes(StandardCharsets.UTF_8);

        System.out.println("Please input the hex to be used as the IV: ");
        String IVstring = scr.nextLine();

        scr.close();

        byte[] iv = hexStringToByteArray(IVstring);

       
        byte[] ciphertext = encryptAESCounterMode(plaintextBytes, secretKey, iv);

       
      
            System.out.println("The HashCode produced by SHA-256 algorithm for strings: ");

            System.out.println("Plaintext: " + plaintext);
            System.out.println("\nCiphertext: " + bytesToHex(ciphertext));
            String sha3Hash = SHA3.sha3_512HexInput(bytesToHex(ciphertext));

            if (sha3Hash != null) {
                System.out.println("\nSHA3-512 Hash: " + sha3Hash);
            } else {
                System.out.println("Error calculating SHA3-512 hash.");
            }

            System.out.println("\nAnswer : " + bytesToHex(ciphertext).substring(0,6) + ":" + sha3Hash.substring(0,6));

    }

     private static SecretKey generateAESKeyFromHex(String keyHex) {
        byte[] keyBytes = hexStringToByteArray(keyHex);
        return new SecretKeySpec(keyBytes, "AES");
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    private static SecretKey generateRandomAESKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // You can also use 192 or 256 bits
        return keyGenerator.generateKey();
    }

    private static byte[] generateRandomIV() {
        return "6bf70def691416316bcaa01925d3840a".getBytes(StandardCharsets.UTF_8);
    }

    private static byte[] encryptAESCounterMode(byte[] plaintext, Key key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        return cipher.doFinal(plaintext);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexStringBuilder = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            hexStringBuilder.append(String.format("%02x", b));
        }
        return hexStringBuilder.toString();
    }
}

  class SHA3 {

    public static String sha3_512HexInput(String hexInput) {
        try {
            // Convert hex string to byte array
            byte[] inputBytes = hexStringToByteArray(hexInput);

            // Using MessageDigest for SHA-3
            MessageDigest digest = MessageDigest.getInstance("SHA3-512");
            byte[] hash = digest.digest(inputBytes);

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
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