package controler;

import java.io.IOException;
import java.time.LocalDate;

import model.AlphaVantageApi;
import model.DataSource;

public class PortfolioPriceOnDate {
  private DataSource api = new DataSource(new AlphaVantageApi());
  public void getPortfolioPriceOnDate(Portfolio portfolio, LocalDate date) throws IOException {
    float total = 0;
    int numStocks = portfolio.getNumStocks();

    for (int i=0; i<numStocks; i++){
      Stock stock = Portfolio.getStock(i);
      total += api.getStockPrice(stock.getStockName(), date)*stock.getStockNumber();
    }

    System.out.println("Price of portfolio on "+date.toString()+" was "+total);
  }
}
