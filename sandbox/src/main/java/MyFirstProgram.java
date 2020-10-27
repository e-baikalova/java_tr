public class MyFirstProgram {

	public static void main (String[] args) {
		hello("world");
		hello("user");

		Square s = new Square(5);
		System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area() );

		Rectangle r = new Rectangle(4.0, 5);
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area() );
	}

	public static void hello(String sText) {
			System.out.println("Hello, " + sText + "!");
	}

//	function
//	public static double area(Square s) {
//		return s.l * s.l;  //square area
//	}

//  function
//	public static double area(Rectangle r) {
//		return r.a * r.b;  //rectangle area
//	}

}