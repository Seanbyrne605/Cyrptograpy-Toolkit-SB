import java.math.BigInteger;
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
        
        decrypt(plaintext, ciphertext, N);
       
    }

    public static void decrypt(String plaintext, String cipherText, int N) {

        int [] plaintextasciiArray = convertStringToAsciiArray(plaintext);
        int [] cipherasciiArray = convertStringToAsciiArray(cipherText);

        int firstplain = plaintextasciiArray[0] * N + plaintextasciiArray[1] ;
        System.out.println("First plain : " + firstplain );
        int firstcipher = cipherasciiArray[0] * N + cipherasciiArray[1];
        System.out.println("First ciper : " + firstcipher );

        int secondplain = plaintextasciiArray[2] * N + plaintextasciiArray[3] ;
        System.out.println("Second plain : " + secondplain );
        int secondcipher = cipherasciiArray[2] * N + cipherasciiArray[3];
        System.out.println("Second ciper : " + secondcipher );

        int combined_plain =  firstplain - secondplain;
        int combines_cipher = firstcipher - secondcipher;

        int a = calculateExpression(combines_cipher,combined_plain,N);
        int leftside = (secondcipher - secondplain*a);
        int Nsquared = N*N;
        int b = 0;

        if (leftside < 0) {
            b = (leftside % Nsquared + Nsquared) % Nsquared;
        } else {
            b = leftside % Nsquared;
        }
        System.out.println(leftside);
        System.out.println(N*N);
        System.out.println("\na = " + a);
        System.out.println("b = " + b );
    }
    
    public static int[] convertStringToAsciiArray(String input) {
        char[] charArray = input.toCharArray();
        int[] asciiArray = new int[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            asciiArray[i] = (int) charArray[i];
        }

        return asciiArray;
    }

    public static int calculateExpression(int x, int y, int N) {
        // Calculate y^-1 mod N^2
        int yInverse = modInverse(y, N * N);

        // Calculate x * (y^-1 mod N^2)
        int result = (x * yInverse) % (N * N);

        return result;
    }

    // Extended Euclidean Algorithm to calculate the modular inverse
    private static int modInverse(int a, int m) {
        int m0 = m;
        int y = 0, x = 1;

        if (m == 1) {
            return 0;
        }

        while (a > 1) {
            int q = a / m;
            int t = m;

            m = a % m;
            a = t;
            t = y;

            y = x - q * y;
            x = t;
        }

        if (x < 0) {
            x += m0;
        }

        return x;
    }



}
