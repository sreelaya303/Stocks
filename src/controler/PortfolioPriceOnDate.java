package controler;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import model.AlphaVantageApi;
import model.DataSource;

/**
 * This class is used to determine the price of the portfolio given a date.
 */
public class PortfolioPriceOnDate {
  private DataSource api = new DataSource(new AlphaVantageApi());

  /**
   * Gives the value of the portfolio on a specific date.
   * @param portfolio
   * @param date
   * @return
   * @throws IOException
   */
  public float getPortfolioPriceOnDate(Portfolio portfolio, LocalDate date) throws IOException {
    float total = 0;
    int numStocks = portfolio.getNumStocks();

    for (int i = 0; i < numStocks; i++) {
      Stock stock = Portfolio.getStock(i);
      LocalDate ld = getPreviousWorkingDay(date);
      total += api.getStockPrice(stock.getStockName(), ld) * stock.getStockNumber();
    }

    return total;
  }

  private static LocalDate getPreviousWorkingDay(LocalDate date) {
    DayOfWeek dayOfWeek = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
    switch (dayOfWeek) {
      case MONDAY:
        return date.minus(3, ChronoUnit.DAYS);
      case SUNDAY:
        return date.minus(2, ChronoUnit.DAYS);
      default:
        return date.minus(1, ChronoUnit.DAYS);

    }
  }
}
