package controler;

import java.time.LocalDate;

public class ShowGraph {
  Portfolio ps;
  LocalDate fromDate;
  LocalDate toDate;
  public ShowGraph(Portfolio ps, LocalDate fromDate, LocalDate toDate) {
    this.ps = ps;
    this.fromDate = fromDate;
    this.toDate = toDate;
  }

  private int calculateScale(float portfolioValue){
    return 0;
  }

  private int periods(LocalDate fromDate, LocalDate toDate){
    int numLines = 0;
    return numLines;
  }

  public void showGraph() {
  }
}
