import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        final int KEY = 8;
        int[] left = { 0, 1, 0 }, right = { 1, 0, 1 }, temp;
        System.out.println("=====WRONG=====");
        for (int r = 1; r <= 3; r++) {
            for (int i = 0; i < right.length; i++) {
                right[i] = left[i] ^ (scramble(right[i], KEY, r));
            }
            System.out.println("ENC" + r + " " + Arrays.toString(left) + " " + Arrays.toString(right));
            temp = left;
            left = right;
            right = temp;
        }
        temp = left;
        left = right;
        right = temp; // swap before decrypt
        for (int r = 3; r >= 1; r--) {
            for (int i = 0; i < right.length; i++) {
                right[i] = left[i] ^ (scramble(right[i], KEY, r));
            }
            System.out.println("DEC" + r + " " + Arrays.toString(left) + " " + Arrays.toString(right));
            temp = left;
            left = right;
            right = temp;
        }
        left = new int[] { 1, 2, 3 };
        right = new int[] { 4, 5, 6 }; // reset
        System.out.println("=====RIGHT=====");
        for (int r = 1; r <= 3; r++) {
            for (int i = 0; i < right.length; i++) {
                left[i] ^= (scramble(right[i], KEY, r));
            }
            System.out.println("ENC" + r + " " + Arrays.toString(left) + " " + Arrays.toString(right));
            temp = left;
            left = right;
            right = temp; // swap after
        }
        for (int r = 3; r >= 1; r--) {
            temp = left;
            left = right;
            right = temp; // swap before on decrypt
            for (int i = 0; i < right.length; i++) {
                left[i] ^= (scramble(right[i], KEY, r));
            }
            System.out.println("DEC" + r + " " + Arrays.toString(left) + " " + Arrays.toString(right));
        }
    }

    private static int scramble(int character, int key, int roundNumber) {
        return (int) Math.pow(2 * roundNumber * key, character) % 15;
    }
}
