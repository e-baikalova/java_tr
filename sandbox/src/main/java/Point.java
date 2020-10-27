public class Point {

  public double x;
  public double y;

  public Point (double x, double y)
  {
    this.x = x;
    this.y = y;
  }

  //    method returns X coordinate
  public double xCoordinate() {
    return this.x;
  }

  //    method returns Y coordinate
  public double yCoordinate() {
    return this.y;
  }

  //    method calculates distance between points
  public static double distance(Point p1, Point p2) {
    double x1 = p1.xCoordinate();
    double x2 = p2.xCoordinate();
    double y1 = p1.yCoordinate();
    double y2 = p2.yCoordinate();
    return Math.sqrt((Math.pow((x2 - x1), 2) + (Math.pow((y2 - y1), 2))));
  }
}
