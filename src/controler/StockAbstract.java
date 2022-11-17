package controler;

import model.AlphaVantageApi;
import model.DataSource;

public abstract class StockAbstract implements Stock {
  protected String stockName;
  protected long stockNumber;
  protected double stockPrice;
  protected int transactions = 1;
  protected DataSource api = new DataSource(new AlphaVantageApi());



  public boolean verifyStockTicker(String ticker) {
    return this.api.checkTicker(ticker);
  }

  public String getStockName() {
    return this.stockName;
  }

  public void setStockName(String stockName) {
    this.stockName = stockName;
  }

  public Long getStockNumber() {
    return this.stockNumber;
  }

  public double getStockPrice() {
    return this.stockPrice;
  }

  public int getTransactions() {
    return this.transactions;
  }

  public void setTransactions() {
    ++this.transactions;
  }

  public void setTransactions(int i) {
    this.transactions = i;
  }

  public void setStockNumber(Long stockNumber) {
    this.stockNumber = stockNumber;
  }
}