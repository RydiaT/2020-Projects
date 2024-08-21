import java.util.Scanner;

public class Main {
    static boolean isPrime(int num){
        if(num == 1 || (num % 2 == 0 && num != 2)){
            return false;
        }

        for(int i = 2; i < num / 2; i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Hello and welcome!\n");

        int primes = 0;

        System.out.print("Enter the length of the prime search: ");
        int length = scanner.nextInt();

        for (int i = 1; i <= length; i++) {
            if(isPrime(i)){
                primes++;
            }
        }

        System.out.printf("%s out of %s were primes.%n", primes, length);
    }
}