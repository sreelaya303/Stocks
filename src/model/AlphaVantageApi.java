package model;

import java.io.IOException;
import java.time.LocalDate;

/**
 * An interface that gets the information from the API.
 */
public interface AlphaVantageApi {
  float getStockPrice(String ticker, LocalDate date) throws IOException;

  boolean checkTicker(String ticker);

  boolean checkDate(LocalDate date);

}
