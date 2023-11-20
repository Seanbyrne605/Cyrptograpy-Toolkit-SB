    /**

     ** Java Program to implement Fermat Factorization Algorithm

     **/

     

     import java.util.Scanner;

     

     public class FermatFactorization
 
     {
 
         /** Fermat factor **/
 
         public void FermatFactor(long N)
 
         {
 
             long a = (long) Math.ceil(Math.sqrt(N));
 
             long b2 = a * a - N;
 
             while (!isSquare(b2))
             {
 
                 a++;
 
                 b2 = a * a - N;
 
             }
 
             long r1 = a - (long)Math.sqrt(b2);
 
             long r2 = N / r1;
 
             display(r1, r2);
 
         }
 
         /** function to display roots **/
 
         public void display(long r1, long r2)
 
         {
 
             System.out.println("\nRoots = "+ r1 +" , "+ r2);    
 
         }
 
         /** function to check if N is a perfect square or not **/
 
         public boolean isSquare(long N)
 
         {
 
             long sqr = (long) Math.sqrt(N);
 
             if (sqr * sqr == N || (sqr + 1) * (sqr + 1) == N)
 
                 return true;
 
             return false;
 
         }
 
         /** main method **/
 
         public static void main(String[] args) 
 
         {
 
             Scanner scan = new Scanner(System.in);
 
             System.out.println("Fermat Factorization Test\n");
 
             System.out.println("Enter odd number");
 
             long N = scan.nextLong();
 
             FermatFactorization ff = new FermatFactorization();
 
             ff.FermatFactor(N);
 
         }
 
     }