
// import statements  
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

// A Java program to find the SHA-256 hash value  
public class SHA {
    public static byte[] obtainSHA(String s) throws NoSuchAlgorithmException {
        // Static getInstance() method is invoked with the hashing SHA-256
        MessageDigest msgDgst = MessageDigest.getInstance("SHA3-512");
        /*
         * algorithms used by message diggest
         * MD2
         * MD5
         * SHA-1
         * SHA-224
         * SHA-256
         * SHA-384
         * SHA-512/224
         * SHA-512/256
         * SHA3-224
         * SHA3-256
         * SHA3-384
         * SHA3-512
         */

        // the digest() method is invoked
        // to compute the message digest of the input
        // and returns an array of byte
        return msgDgst.digest(s.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexStr(byte[] hash) {
        // Converting the byte array in the signum representation
        BigInteger no = new BigInteger(1, hash);

        // Converting the message digest into the hex value
        StringBuilder hexStr = new StringBuilder(no.toString(16));

        // Padding with tbe leading zeros
        while (hexStr.length() < 32) {
            hexStr.insert(0, '0');
        }

        return hexStr.toString();
    }

    // main method
    public static void main(String argvs[]) {
        try {
            System.out.println("The HashCode produced by SHA-256 algorithm for strings: ");

            String str = "10007";
            String hash = toHexStr(obtainSHA(str));
            System.out.println("\n" + str + " : " + hash);

            String keep = hash.substring(0, 6); // Use 6 instead of 7 to get the first 6 digits
            String latest;
            int number = Integer.parseInt(str);

            do {
                number++;
                latest = toHexStr(obtainSHA(str)).substring(0, 6);
            } while (!keep.equals(latest));

            System.out.println("Second string with the same first 6 digits: " + number);
            str = "17541457";
            hash = toHexStr(obtainSHA(str));
            System.out.println("\n" + str + " : " + hash);
        }
        // handling exception
        // usually raised when some absurd algorithm is used for doing the hash work
        catch (NoSuchAlgorithmException obj) {
            System.out.println("An exception is generated for the incorrect algorithm: " + obj);
        }
    }
}