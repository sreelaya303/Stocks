package controler;

public interface Stock {
  public boolean verifyStockTicker(String ticker);


  public String getStockName();


  public void setStockName(String stockName);

  public Long getStockNumber();

  public double getStockPrice();

  public int getTransactions();

  public void setTransactions();

  public void setTransactions(int i);

  public void setStockNumber(Long stockNumber);
}
