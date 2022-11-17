package controler;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

/**
 * This class is used to show the value of the portfolio over a period of time as a graph.
 */
public class ShowGraph {
  Portfolio ps;
  LocalDate fromDate;
  LocalDate toDate;
  List<Float> prices = new ArrayList<>();
  List<LocalDate> periods = new ArrayList<>();

  /**
   * This is a constructor for the class which sets the portfolio and the period
   * over which the graph has to be shown for.
   *
   * @param ps       is the portfolio.
   * @param fromDate the start date of the period.
   * @param toDate   the end date of the period.
   */
  public ShowGraph(Portfolio ps, LocalDate fromDate, LocalDate toDate) {
    this.ps = ps;
    this.fromDate = fromDate;
    this.toDate = toDate;

  }

  /**
   * Shows the value of the portfolio over a period of time as a graph
   * deciding the scale and timestamps for each portfolio based on the period specified.
   *
   * @throws IOException if the input is invalid.
   */
  public void showGraph() throws IOException {
    getPeriods(this.fromDate, this.toDate);
    int s = calculateScale(this.ps);
    System.out.println("Performance of portfolio " + this.ps.getPortfolioName() + " from "
            + this.fromDate + " to " + this.toDate);
    int k = periods.size();
    for (int i = 0; i < k; i++) {
      int numAst = 1;
      if (prices.get(i) < 0) {
        LocalDate temp = getPreviousWorkingDay(periods.get(i));
        PortfolioPriceOnDate psd = new PortfolioPriceOnDate();
        float tempPrice = psd.getPortfolioPriceOnDate(this.ps, temp);
        if (tempPrice < 0) {
          temp = getPreviousWorkingDay(now());
          tempPrice = psd.getPortfolioPriceOnDate(this.ps, temp);
        }
        numAst = (int) (tempPrice / s);
        System.out.println(temp + ": " + "*".repeat(numAst));
      } else {
        numAst = (int) ((prices.get(i)) / s);
        System.out.println(periods.get(i) + ": " + "*".repeat(numAst));
      }
    }
    System.out.println("Scale: * = $" + s);
  }

  private int calculateScale(Portfolio ps) throws IOException {
    PortfolioPriceOnDate ppd = new PortfolioPriceOnDate();
    float max = 0.0F;
    for (LocalDate localDate : periods) {
      float f = ppd.getPortfolioPriceOnDate(ps, localDate);
      prices.add(f);
      if (f > max) {
        max = f;
      }
    }
    DecimalFormat df = new DecimalFormat("#");

    return Integer.parseInt(df.format(max / 30));
  }

  private void getPeriods(LocalDate fromDate, LocalDate toDate) {
    long daysBetween = DAYS.between(fromDate, toDate);
    if (daysBetween <= 30) {
      periods = fromDate.datesUntil(toDate).collect(Collectors.toList());
    } else if (daysBetween <= 913) {
      List<LocalDate> allDates = fromDate.datesUntil(toDate).collect(Collectors.toList());
      List<LocalDate> duplicatePeriods = new ArrayList<>();
      for (LocalDate d : allDates) {
        duplicatePeriods.add(d.withDayOfMonth(d.getMonth().length(d.isLeapYear())));
      }
      periods = new ArrayList<>(new LinkedHashSet<>(duplicatePeriods));
    } else {
      List<LocalDate> allDates = fromDate.datesUntil(toDate).collect(Collectors.toList());
      List<LocalDate> duplicatePeriods = new ArrayList<>();
      for (LocalDate d : allDates) {
        duplicatePeriods.add(d.with(lastDayOfYear()));
      }
      periods = new ArrayList<>(new LinkedHashSet<>(duplicatePeriods));
    }
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
