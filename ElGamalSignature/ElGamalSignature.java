import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Scanner;

public class ElGamalSignature {

    public static void main(String[] args) {
        try {
            // Generate key pair
            KeyPair keyPair = generateKeyPair();

            // Get the private and public keys
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // Create a message to be signed
            Scanner scr = new Scanner(System.in);

            System.out.println("Please enter input string:");
            String input = scr.nextLine();

            scr.close();
            byte[] message = input.getBytes();

            // Generate a signature
            byte[] signature = generateSignature(message, privateKey);

            // Verify the signature
            boolean isVerified = verifySignature(message, signature, publicKey);

            // Print the result
            System.out.println("Is Signature Verified? " + isVerified);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Generate an ElGamal key pair
    private static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1"); // You can adjust the curve name
        keyPairGenerator.initialize(ecSpec);
        return keyPairGenerator.generateKeyPair();
    }

    // Generate an ElGamal signature
    private static byte[] generateSignature(byte[] message, PrivateKey privateKey) throws Exception {
         // change the with to DSA if required
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        signature.update(message);
        return signature.sign();
    }

    // Verify an ElGamal signature
    private static boolean verifySignature(byte[] message, byte[] signature, PublicKey publicKey) throws Exception {
        // change the with to DSA if required
        Signature verifier = Signature.getInstance("SHA256withECDSA");
        verifier.initVerify(publicKey);
        verifier.update(message);
        return verifier.verify(signature);
    }
}
