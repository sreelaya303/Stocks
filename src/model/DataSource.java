package model;

import java.io.IOException;
import java.time.LocalDate;

public class DataSource implements ApiInterface{
  private ApiInterface source;

  public DataSource(ApiInterface src){
    this.source = src;
  }

  void setDataSource(ApiInterface src){
    this.source = src;
  }

  @Override
  public float getStockPrice(String ticker, LocalDate date) throws IOException {
    return source.getStockPrice(ticker, date);
  }

  @Override
  public boolean checkTicker(String ticker) {
    return source.checkTicker(ticker);
  }
}
