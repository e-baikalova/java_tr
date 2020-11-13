import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {

  @Test(enabled = false)
  public void testZeroPrime(){
    Assert.assertTrue(Primes.isPrimeWhile(0));
  }

  @Test(enabled = false)
  public void testOnePrime(){
    Assert.assertTrue(Primes.isPrimeWhile(1));
  }

  @Test(enabled = false)
  public void testMinusOnePrimes(){
    Assert.assertTrue(Primes.isPrimeWhile(-1));
  }
  @Test(enabled = false)
  public void testNonPrimes(){
    Assert.assertFalse(Primes.isPrimeWhile(-6));
  }

  @Test
  public void testPrimes(){
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
//    Assert.assertTrue(Primes.isPrime(11));
  }

  @Test(enabled = false)
  public void testPrimesLong(){
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
//    Assert.assertTrue(Primes.isPrime(11));
  }

  @Test
  public void testPrimesFast(){
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
//    Assert.assertTrue(Primes.isPrime(11));
  }
}
