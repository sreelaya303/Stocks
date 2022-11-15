package controler;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * This class gives information about the given portfolio.
 */
public class ExamineComposition {

  /**
   * Gives the details of the portfolio, and it's composition.
   *
   * @param portfolio is the list of stocks and other details about the user portfolio.
   */
  public void analysePortfolio(Portfolio portfolio) {

    System.out.println("Name of the portfolio: " + portfolio.getPortfolioName());
    System.out.println("Number of Unique Stocks: " + portfolio.getMyStocks().size());
    System.out.println("Portfolio was created on: " + portfolio.getDateOfCreation());
    System.out.println("List of the stocks:");
    getAllStocks(portfolio);
    System.out.println("Total number of stocks: " + totalStocks(portfolio));
    System.out.println("Composition of each stock:");
    System.out.println("Cost basis: " + costBasis(portfolio));
    percentageEachStock(portfolio);
  }

  private String getPortfolioName(String path) {
    Path pathf = Paths.get(path);
    Path fileName = pathf.getFileName();
    String strFn = fileName.toString();
    int pos = strFn.lastIndexOf(".");
    if (pos == -1) {
      return strFn;
    }
    return strFn.substring(0, pos);
  }

  private void getAllStocks(Portfolio p) {
    List<Stock> lps = p.getMyStocks();
    int k = lps.size();
    for (Stock lp : lps) {
      System.out.println(lp.getStockName());
    }
  }

  private int totalStocks(Portfolio p) {
    List<Stock> lps = p.getMyStocks();
    int count = 0;
    int k = lps.size();
    for (int i = 0; i < k; i++) {
      count += lps.get(i).getStockNumber();
    }
    return count;
  }

  private void percentageEachStock(Portfolio p) {
    List<Stock> lps = p.getMyStocks();
    int k = lps.size();
    float ts = totalStocks(p);
    for (int i = 0; i < k; i++) {
      float per = (lps.get(i).getStockNumber() / ts) * 100;
      System.out.println(lps.get(i).getStockName() + " is " + per + "% of total stocks.");
    }

  }

  private double costBasis(Portfolio p){
    List<Stock> lps = p.getMyStocks();
    int k = lps.size();
    double cost = 0.0;
    //TODO: Add commision
    for (int i = 0; i < k; i++) {
      Stock s = lps.get(i);
      cost += s.getStockNumber() * s.getStockPrice();
    }
    return cost;
  }

}


