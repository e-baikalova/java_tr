import static java.lang.Math.abs;

public class Primes {

  public static boolean isPrime(int n) {
    n = abs(n);
    if ((n == 0) || (n == 1)){
      return true;
    } else {
        for (int i = 2; i < n; i++) {
          if (n % i == 0) {   //получение остатка
            return false;
          }
        }
        return true;
      }
  }

  public static boolean isPrimeWhile(int n) {
    int i = 2;
    n = abs(n);
    if ((n == 0) || (n == 1)) {
      return true;
    }
    while ((i < n) && (n % i != 0)) {
      i++;
    }
    return i == n;
  }

  public static boolean isPrime(long n) {
    n = abs(n);
    if ((n == 0) || (n == 1)) {
      return true;
    } else {
      for (long i = 2; i < n; i++) {
        if (n % i == 0) {   //получение остатка
          return false;
        }
      }
      return true;
    }
  }

  public static boolean isPrimeFast(int n) {
    n = abs(n);
    int m = (int) Math.sqrt(n);
    if ((n == 0) || (n == 1)) {
      return true;
    } else {
//            for (int i = 2; i < n / 2; i++) {
      for (int i = 2; i < m; i++) {
        if (n % i == 0) {   //получение остатка
          return false;
        }
      }
      return true;
    }
  }
}
