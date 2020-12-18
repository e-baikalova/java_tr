package stqa;

import com.lavasoft.GeoIPService;
import com.lavasoft.GeoIPServiceSoap;
import com.lavasoft.GetLocationResponse;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class IpServiceTests {

  @Test
  public void testMyIp() {
    String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("194.28.29.152");
    System.out.println(geoIp);
    //    assertEquals(geoIp.getCountryNameByISO2(), "RUS");
  }

  @Test
  public void testInvalidIp() {
    String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("194.28.29.aaa");
    System.out.println(geoIp);
    //    assertEquals(geoIp.getCountryCode(), "RUS");
  }
}
