package controler;

import java.io.IOException;
import java.time.LocalDate;
import model.AlphaVantageApi;
import model.AlphaVantageApiImpl;

/**
 * A Stock class that has the name, number and price of the stock.
 */
public class Stock {
  private String stockName;
  private Long stockNumber;
  private float stockPrice;

  private AlphaVantageApi api = new AlphaVantageApiImpl();

  /**
   * An empty constructor for the given class.
   */
  public Stock() {
    //No initialization
  }

  /**
   * A constructor for the class Stock.
   *
   * @param name  of the stock.
   * @param num   is the number of said stocks.
   * @param price is the price of the stocks.
   */
  public Stock(String name, long num, float price) {
    this.stockName = name;
    this.stockNumber = num;
    this.stockPrice = price;
  }


  /**
   * Gets the name of the stock.
   *
   * @return name of the stock.
   */
  public String getStockName() {
    return stockName;
  }

  /**
   * Sets the name of the stock.
   *
   * @param stockName is the name of the stock.
   */
  public void setStockName(String stockName) {
    this.stockName = stockName;
  }

  /**
   * Gets the number of stocks.
   *
   * @return number of stocks.
   */
  public Long getStockNumber() {
    return stockNumber;
  }

  /**
   * Sets the number of the stocks.
   *
   * @param stockNumber number of the stocks.
   */
  public void setStockNumber(Long stockNumber) {
    this.stockNumber = stockNumber;
  }

  /**
   * Gets the price of the stock.
   *
   * @return price of the stock.
   */
  public float getStockPrice() {
    return stockPrice;
  }

  /**
   * Set the price of the stock.
   *
   * @param stockPrice price of the stock.
   */
  public void setStockPrice(float stockPrice) {
    this.stockPrice = stockPrice;
  }

  /**
   * Check whether a stock exists.
   *
   * @param ticker name of the stock.
   * @return true if it is valid.
   */
  public boolean verifyStockTicker(String ticker) {
    return api.checkTicker(ticker);
  }

  /**
   * Gets the stock price on that particular date.
   *
   * @param date specified by the user.
   * @return the price of the stock on that day.
   * @throws IOException if the date is invalid.
   */
  public float getStockPriceOnDate(LocalDate date) throws IOException {
    return api.getStockPrice(this.stockName, date);
  }
}
