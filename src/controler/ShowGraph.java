package controler;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

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
    int numLines = 0;
    long daysBetween = DAYS.between(fromDate, toDate);
//    if (daysBetween <= 30){
//      List<LocalDate> dates = fromDate.datesUntil(toDate).collect(Collectors.toList());
//      System.out.println(dates.toString());
//    }else if (daysBetween > 30 && daysBetween <= 913){
//      List<String> months = new ArrayList<>();
//      String fd = fromDate.toString();
//      String td = toDate.toString();
//      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM", Locale.ENGLISH);
//      YearMonth startDate = YearMonth.parse(fd.substring(0, 7), formatter);
//      YearMonth endDate = YearMonth.parse(td.substring(0, 7), formatter);
//      while(startDate.isBefore(endDate)) {
//        months.add(startDate.format(formatter));
//        startDate = startDate.plusMonths(1);
//      }
//      assert months != null;
//      System.out.println(months.toString());

//    }else if (daysBetween > 913){
//      //Show each year
//    }
    periods = fromDate.datesUntil(toDate).collect(Collectors.toList());
    return periods;
  }

  public void showGraph() throws IOException {
    getPeriods(this.fromDate, this.toDate);
    int s = calculateScale(this.ps);
    System.out.println("Performance of portfolio "+this.ps.getPortfolioName()+" from "
            +this.fromDate+ " to " +this.toDate);
    int k = periods.size();
    for(int i = 0; i < k; i++){
      if(prices.get(i)<0){
        continue;
      }
      int numAst = (int) ((prices.get(i))/s);
      System.out.println(periods.get(i) + ": " + "*".repeat(numAst));
    }
    System.out.println("Scale: * = $" +s);
  }
  
}
