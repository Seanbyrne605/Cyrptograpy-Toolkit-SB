package AES;

    import javax.crypto.Cipher;  
    import javax.crypto.SecretKey;  
    import javax.crypto.SecretKeyFactory;  
    import javax.crypto.spec.IvParameterSpec;  
    import javax.crypto.spec.PBEKeySpec;  
    import javax.crypto.spec.SecretKeySpec;  
    import java.nio.charset.StandardCharsets;  
    import java.security.InvalidAlgorithmParameterException;  
    import java.security.InvalidKeyException;  
    import java.security.NoSuchAlgorithmException;  
    import java.security.spec.InvalidKeySpecException;  
    import java.security.spec.KeySpec;  
    import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;  
    import javax.crypto.IllegalBlockSizeException;  
    import javax.crypto.NoSuchPaddingException;  
    public class AES  
    {  
        /* Private variable declaration */  
        private static final String SECRET_KEY = " e33bbfcb96deaa8513fe60757b546bf4";  
        private static final String SALTVALUE = "";  
       
        /* Encryption Method */  
        public static String encrypt(String strToEncrypt)   
        {  
        try   
        {  
          /* Declare a byte array. */  
          byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0} ;  
          IvParameterSpec ivspec = new IvParameterSpec(iv);        
          /* Create factory for secret keys. */  
          SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");  
          /* PBEKeySpec class implements KeySpec interface. */  
          KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALTVALUE.getBytes(), 65536, 256);  
          SecretKey tmp = factory.generateSecret(spec);  
          SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
          
          System.out.println("Please enter the type of AES you would like to use:");
          Scanner scr= new Scanner(System.in);
          String s = scr.nextLine();
          scr.close();
          Cipher cipher = Cipher.getInstance("AES/" + s + "/PKCS5Padding");
          
          if(s.equals("ECB") || s.equals("ecb")|| s.equals("GCM") || s.equals("gcm"))
          {
          cipher.init(Cipher.ENCRYPT_MODE, secretKey);
   
          }
          else{cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);}  
          /* Retruns encrypted value. */  
          return Base64.getEncoder()  
    .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));  
        }   
        catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e)   
        {  
          System.out.println("Error occured during encryption: " + e.toString());  
        }  
        return null;  
        }  
        
        /* Decryption Method */  
        public static String decrypt(String strToDecrypt)   
        {  
        try   
        {  
          /* Declare a byte array. */  
          byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};  
          IvParameterSpec ivspec = new IvParameterSpec(iv);  
          /* Create factory for secret keys. */  
          SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");  
          /* PBEKeySpec class implements KeySpec interface. */  
          KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALTVALUE.getBytes(), 65536, 256);  
          SecretKey tmp = factory.generateSecret(spec);  
          SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");  
          Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");  
          cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);  
          /* Retruns decrypted value. */  
          return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));  
        }   
        catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e)   
        {  
          System.out.println("Error occured during decryption: " + e.toString());  
        }  
        return null;  
        }  
        /* Driver Code */  
        public static void main(String[] args)   
        {  
            /* Message to be encrypted. */  
            String originalval = "<XML>\r\n" + //
                "\r\n" + //
                "  <CreditCardPurchase>\r\n" + //
                "\r\n" + //
                "    <Merchant>Bryanair INC.</Merchant>\r\n" + //
                "\r\n" + //
                "    <Buyer>Ivor Lott</Buyer>\r\n" + //
                "\r\n" + //
                "    <Date>01/10/2022</Date>\r\n" + //
                "\r\n" + //
                "    <Amount>10000.00 Euro</Amount>\r\n" + //
                "\r\n" + //
                "    <CCNumber>1010-9010-3412-4653</CCNumber>\r\n" + //
                "\r\n" + //
                "  </CreditCardPurchase>\r\n" + //
                "\r\n" + //
                "</XML>";  
            /* Call the encrypt() method and store result of encryption. */  
            String encryptedval = encrypt(originalval);  
            /* Call the decrypt() method and store result of decryption. */  
            String decryptedval = decrypt(encryptedval);  
            /* Display the original message, encrypted message and decrypted message on the console. */  
            System.out.println("Original value: " + originalval);  
            System.out.println("Encrypted value: " + encryptedval);  
            System.out.println("Decrypted value: " + decryptedval);  
        }  
    }  