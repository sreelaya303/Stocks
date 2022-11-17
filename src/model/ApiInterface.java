package model;

import java.io.IOException;
import java.time.LocalDate;

/**
 * An interface that gets the stock information like name and price from the API.
 */
public interface ApiInterface {
  float getStockPrice(String ticker, LocalDate date) throws IOException;

  boolean checkTicker(String ticker);

}
