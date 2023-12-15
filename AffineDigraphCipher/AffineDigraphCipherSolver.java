import java.util.Scanner;

public class AffineDigraphCipherSolver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get input from the user
        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter the ciphertext: ");
        String ciphertext = scanner.nextLine();

        System.out.print("Enter the modulus N: ");
        int N = scanner.nextInt();

        scanner.close();
        /*AB[0] = a value
         * AB[1] =b value
         */
        int[] AB = new int[2];

        //loop all coprimes to N this will be a value
        for(int i =0; i <= N;i++)
        {
            //loop all coprimes until N this will be b value
            for(int j =0; j <=N; j++)
            {
                if(encrypt(plaintext, i, j, N).equals(ciphertext) == true)
                {
                  

                    System.out.println(encrypt(plaintext, i, j, N));
                    System.out.println(ciphertext);
                    System.out.println("\na= " + i);
                    System.out.println("b= " + j);
                }
                
            }
        }
       
    }

    public static String encrypt(String plaintext, int a, int b, int modulus) {
        StringBuilder encrypted = new StringBuilder();
    
        for (int i = 0; i < plaintext.length(); i += 2) {
            char firstChar = plaintext.charAt(i);
            char secondChar = (i + 1 < plaintext.length()) ? plaintext.charAt(i + 1) : 'X'; // Pad with 'X' if needed
    
            int leftsideFirst = (a * firstChar) + b;
            int leftsideSecond = (a * secondChar) + b;
    
            int encryptedFirstChar = leftsideFirst % modulus;
            int encryptedSecondChar = leftsideSecond % modulus;
    
            encrypted.append((char) encryptedFirstChar).append((char) encryptedSecondChar);
        }
    
        return encrypted.toString();
    }
    



}
