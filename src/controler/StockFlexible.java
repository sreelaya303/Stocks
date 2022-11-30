package controler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class StockFlexible extends StockAbstract {
  ArrayList<Transactions> activity = new ArrayList();

  public StockFlexible() {
  }

  public void addTransaction(Transactions t) {
    this.activity.add(t);
  }

  public float getCostBasis(LocalDate date) {
    return 0.0F;
  }

  public float getStockPriceOnDate(LocalDate date) throws IOException {
    return this.api.getStockPrice(this.stockName, date);
  }

  public Transactions getTransactions(int i) {
    return (Transactions)this.activity.get(i);
  }

  public int numTransactions() {
    return this.activity.size();
  }
}