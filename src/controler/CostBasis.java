package controler;

public class CostBasis {
  public CostBasis() {
  }

  public float getCostBasis(Portfolio portfolio) {
    float total = 0;
    int numStocks = portfolio.getNumStocks();

    for (int i = 0; i < numStocks; i++) {
      Stock stock = Portfolio.getStock(i);

      total += ((stock.getStockNumber()*stock.getStockPrice())-(stock.getTransactions()-portfolio.getCommission()));
    }
    return total;
  }
}
