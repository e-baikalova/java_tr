import java.lang.Math;

public class PointsOperations {

  public static void main(String[] args) {
    Point p1 = new Point(1, 0);
    System.out.println("Задана точка 1 (" + p1.x + " ; " + p1.y + " )");
    Point p2 = new Point(1, 5);
    System.out.println("Задана точка 2 (" + p2.x + " ; " + p2.y + " )");

    //points comparison
    if (p1.x == p2.x && p1.y == p2.y) {
      System.out.println("Координаты точек равны. Расстояние между точками  = 0");
    } else {
      System.out.println("Координаты точек не равны");
      System.out.println("Расстояние между точками (через функцию )= " + distance(p1, p2));
//      System.out.println("Расстояние между точек (через методы xCoordinate и yCoordinate) = " +
//          Math.sqrt(
//              Math.pow((p2.xCoordinate() - p1.xCoordinate()), 2) +
//                  Math.pow((p2.yCoordinate() - p1.yCoordinate()), 2))
//      );
//      System.out.println("Расстояние между точек (через метод distance в классе Point) = " + Point.distance (p1, p2));
      System.out.println("Расстояние между точек (через метод distance в классе Point, передается 1 точка) = " + p1.distance (p2));
    }
  }

  //  function to calculate distance between points
  public static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
  }

}
