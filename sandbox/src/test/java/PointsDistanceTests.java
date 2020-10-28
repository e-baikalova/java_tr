import org.testng.Assert;
import org.testng.annotations.Test;

public class PointsDistanceTests {

  @Test
  public void testDistanceOnZeroPoints() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(p1.distance(p2), 0);
  }

  @Test
  public void testDistanceOnSamePoints() {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(1, 2);
    Assert.assertEquals(p1.distance(p2), 0);
  }

  @Test
  public void testDistanceOnSameYCoordinatesPoints() {
    Point p1 = new Point(-1, 2);
    Point p2 = new Point(1, 2);
    Assert.assertEquals(p1.distance(p2), 2);  //
  }

  @Test
  public void testDistanceOnDifferentPoints() {
    Point p1 = new Point(-1, 1);
    Point p2 = new Point(3, 2);
    Assert.assertEquals(p1.distance(p2), 5.0);  //incorrect expected value
  }
}
