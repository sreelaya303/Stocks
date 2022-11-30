package controler;

import java.io.IOException;
import java.time.LocalDate;

import model.DataSource;
import model.AlphaVantageApi;

/**
 * A Stock class that has the name, number and price of the stock.
 */
public class Stock {
  private String stockName;
  private long stockNumber;
  private double stockPrice;

  private int transactions = 1;

  /**
   * Setting the data source to alphaVantageApi, this can later be changed by setSource.
   * the user needs to be aware of the type of sources available.
   * we can add an Enum with all available sources later.
   */
  private DataSource api = new DataSource(new AlphaVantageApi());


  /**
   * An empty constructor for the Stock class.
   */
  public Stock() {
    this.stockName = "";
    this.stockPrice = 0.0;
    this.stockNumber = 0;

  }

  /**
   * A constructor for the class Stock.
   *
   * @param name  of the stock.
   * @param num   is the number of said stocks.
   * @param price is the price of the stocks.
   */
  public Stock(String name, long num, double price) {
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
  public double getStockPrice() {
    return stockPrice;
  }

  /**
   * Set the price of the stock.
   *
   * @param stockPrice price of the stock.
   */
  public void setStockPrice(double stockPrice) {
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

  public void addTrans(){
    this.transactions += 1;
  }

  public int getTransactions(){
    return this.transactions;
  }

  public void setTransactions(int temp){
    this.transactions = temp;
  }
}
