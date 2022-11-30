import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;

import model.AlphaVantageApi;
import model.ApiInterface;

public class tests {
  @Test
  public void testApi()
  {
    LocalDate lt = LocalDate.parse("2018-12-27");
    ApiInterface api = new AlphaVantageApi();
    try {
      float temp = api.getStockPrice("AAPL", lt);
      System.out.println(temp);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
