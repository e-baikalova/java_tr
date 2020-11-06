public class StringsComparison {

  public static void main(String[] args) {
    String s1 = "firefox";
    String s2 = new String(s1);

    System.out.println(s1 == s2);  //links comparison
    System.out.println(s1.equals(s2));  //content comparison

  }
}
