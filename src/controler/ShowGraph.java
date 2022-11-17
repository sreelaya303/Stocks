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

import model.ReadWriteToFile;

import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

public class ShowGraph {
  Portfolio ps;
  LocalDate fromDate;
  LocalDate toDate;
  List<Float> prices = new ArrayList<>();
  List<LocalDate> periods = new ArrayList<>();

  public ShowGraph(Portfolio ps, LocalDate fromDate, LocalDate toDate) {
    this.ps = ps;
    this.fromDate = fromDate;
    this.toDate = toDate;

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
    int scale = Integer.parseInt(df.format(max/30));

    return scale;
  }

  private List<LocalDate> getPeriods(LocalDate fromDate, LocalDate toDate){
    long daysBetween = DAYS.between(fromDate, toDate);
    if (daysBetween <= 30){
      periods = fromDate.datesUntil(toDate).collect(Collectors.toList());
    }else if (daysBetween > 30 && daysBetween <= 913){
      List<LocalDate> allDates = fromDate.datesUntil(toDate).collect(Collectors.toList());
      List<LocalDate> duplicatePeriods = new ArrayList<>();
      for(LocalDate d : allDates){
        duplicatePeriods.add(d.withDayOfMonth(d.getMonth().length(d.isLeapYear())));
      }
      periods = new ArrayList<>(new LinkedHashSet<>(duplicatePeriods));
    }else if (daysBetween > 913){
      List<LocalDate> allDates = fromDate.datesUntil(toDate).collect(Collectors.toList());
      List<LocalDate> duplicatePeriods = new ArrayList<>();
      for(LocalDate d : allDates){
        duplicatePeriods.add(d.with(lastDayOfYear()));
      }
      periods = new ArrayList<>(new LinkedHashSet<>(duplicatePeriods));
      System.out.println(periods.toString());
    }
    return periods;
  }

  public void showGraph() throws IOException {
    getPeriods(this.fromDate, this.toDate);
    int s = calculateScale(this.ps);
    System.out.println("Performance of portfolio "+this.ps.getPortfolioName()+" from "
            +this.fromDate+ " to " +this.toDate);
    int k = periods.size();
    for(int i = 0; i < k; i++){
      int numAst = 1;
      if(prices.get(i)<0){
        LocalDate temp = getPreviousWorkingDay(periods.get(i));
        PortfolioPriceOnDate psd = new PortfolioPriceOnDate();
        float tempPrice = psd.getPortfolioPriceOnDate(this.ps, temp);
        if (tempPrice<0){
          temp = getPreviousWorkingDay(now());
          tempPrice = psd.getPortfolioPriceOnDate(this.ps, temp);
        }
        numAst = (int) (tempPrice/s);
        System.out.println(temp+ ": " + "*".repeat(numAst));
      }
      else{
        numAst = (int) ((prices.get(i))/s);
        System.out.println(periods.get(i) + ": " + "*".repeat(numAst));
      }
    }
    System.out.println("Scale: * = $" +s);
  }
  public static LocalDate getPreviousWorkingDay(LocalDate date) {
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

  public static void main(String[] args) throws IOException {
    ReadWriteToFile rw = new ReadWriteToFile();
    Portfolio p = rw.readFile("/Users/laya/Blehh/Projects/5010/group/Stocks/src/model/Portfolios/parijat.txt");
    LocalDate fd = LocalDate.parse("2011-11-01");
    LocalDate td = LocalDate.parse("2022-10-10");
    ShowGraph sg = new ShowGraph(p, fd, td);
    sg.showGraph();
  }
}
