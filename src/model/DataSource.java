package model;

import java.io.IOException;
import java.time.LocalDate;

/**
 * This class implements the ApiInterface methods
 * i.e, getStockPrice and checkTicker.
 */
public class DataSource implements ApiInterface {
  private ApiInterface source;

  /**
   * Specifies the API for the data source.
   *
   * @param src is the source of the API.
   */
  public DataSource(ApiInterface src) {
    this.source = src;
  }

  void setDataSource(ApiInterface src) {
    this.source = src;
  }

  /**
   * Gets the price of the stock given name and date.
   *
   * @param ticker is the name of the stock.
   * @param date   is the date the price has to be fetched on.
   * @return the price of the stock.
   * @throws IOException if the input is invalid.
   */
  @Override
  public float getStockPrice(String ticker, LocalDate date) throws IOException {
    return source.getStockPrice(ticker, date);
  }

  /**
   * Check if the ticker exists in the API.
   *
   * @param ticker is the name of the stock.
   * @return true if the stock name is valid else false.
   */
  @Override
  public boolean checkTicker(String ticker) {
    return source.checkTicker(ticker);
  }
}
