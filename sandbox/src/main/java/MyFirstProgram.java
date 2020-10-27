public class MyFirstProgram {

	public static void main (String[] args) {
		hello("world");
		hello("user");

		double l = 5.0;  //rectangle length
		System.out.println("Площадь квадрата со стороной " + l + " = " + area(l));

		double a = 5.0;
		double b = 4.0;
		System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + "= " + area(a, b));
	}

	public static void hello(String sText) {
			System.out.println("Hello, " + sText + "!");
	}

	public static double area(double len) {
		return len * len;  //rectangle square
	}

	public static double area(double a, double b) {
		return a * b;  //rectangle square
	}

}