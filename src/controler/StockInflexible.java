package controler;

import java.io.IOException;
import java.time.LocalDate;

public class StockInflexible extends StockAbstract {
  public StockInflexible() {
  }

  public StockInflexible(String name, long num, double price) {
    this.stockNumber = num;
    this.stockPrice = price;
  }

  public void setStockPrice(double stockPrice) {
    this.stockPrice = stockPrice;
  }

  public float getStockPriceOnDate(LocalDate date) throws IOException {
    return this.api.getStockPrice(this.stockName, date);
  }
}

