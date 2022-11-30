package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import controler.Portfolio;
import controler.Stock;


/**
 * Writes the portfolios to a file and reads the portfolio given the path of the file.
 */
public class ReadWriteToFile {
  private static FileWriter file;

  /**
   * Reads the file and sets it as a portfolio when path is given.
   *
   * @param filePath the path of the file.
   * @return the portfolio from the path.
   */
  public Portfolio readFile(String filePath) {
    Portfolio pnew = new Portfolio();
    JSONParser parser = new JSONParser();
    try {
      JSONObject port = (JSONObject) parser.parse(new FileReader(filePath));
      File f = new File(filePath);
      pnew.setName((f.getName()).replaceFirst("[.][^.]+$", ""));
      pnew.setDateOfCreation(LocalDate.parse((String) port.get("Date")));
      pnew.setCommission(Float.parseFloat((String) port.get("Fees")));
      pnew.setFlexible(f.canWrite());
      JSONArray stockList = (JSONArray) port.get("Stocks");
      for (JSONObject obj : (Iterable<JSONObject>) stockList) {
        Stock ps = new Stock((String) obj.get("Name"), (Long) obj.get("Number"),
                (Double) obj.get("Price"));
        ps.setTransactions(Integer.parseInt(obj.get("Transactions").toString()));
        pnew.addStocks(ps);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pnew;
  }

  /**
   * Writes the portfolio to a text file.
   *
   * @param portfolio takes the object Portfolio.
   */
  public void writeToFile(Portfolio portfolio) {
    File dir = new File("./src/model/Portfolios");
    dir.mkdirs();
    JSONObject port = new JSONObject();
    port.put("Date", String.valueOf(portfolio.getDateOfCreation()));
    port.put("Fees", String.valueOf(portfolio.getCommission()));
    JSONArray allStocks = new JSONArray();
    for (int i = 0; i < portfolio.getNumStocks(); i++) {
      JSONObject obj = new JSONObject();
      Stock temp = Portfolio.getStock(i);
      obj.put("Name", temp.getStockName());
      obj.put("Number", temp.getStockNumber());
      obj.put("Price", temp.getStockPrice());
      obj.put("Transactions", temp.getTransactions());
      allStocks.add(obj);
    }
    port.put("Stocks", allStocks);

    try {
      file = new FileWriter("./src/model/Portfolios/" + portfolio.getPortfolioName() + ".txt");
      file.write(port.toJSONString());
      if (!portfolio.getFlexible()) {
        File f = new File("./src/model/Portfolios/" + portfolio.getPortfolioName() + ".txt");
        f.setReadOnly();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        file.flush();
        file.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  /**
   * Enables buying and selling stocks of a flexible portfolio.
   *
   * @param ps              is the given portfolio from which stocks are to be bought or sold.
   * @param name            is the name of the stock.
   * @param buySell         specifies whether to buy or sell the stock.
   * @param quantity        is number of the stocks to perform this transaction.
   * @param transactionDate is the date on which this transaction has to be made.
   */
  public void buySellStocks(Portfolio ps, String name, String buySell, double quantity,
                            LocalDate transactionDate) {
    List<Stock> ls = ps.getMyStocks();
    for (int i = 0; i < ls.size(); i++) {
      String s = ls.get(i).getStockName();
      if (Objects.equals(s, name)) {
        Long j = ls.get(i).getStockNumber();
        if (Objects.equals(buySell, "buy")) {
          ls.get(i).setStockNumber((long) (j + quantity));
          ls.get(i).addTrans();
        } else if (Objects.equals(buySell, "sell")) {
          if (j < quantity) {
            System.out.println("The quantity of stocks exceeds the existing stocks. Can't sell.");
          } else {
            ls.get(i).setStockNumber((long) (j - quantity));
            ls.get(i).addTrans();
          }
        }
        break;
      }
    }
    writeToFile(ps);
  }

  /**
   * Checks if the portfolio already exists.
   *
   * @param portfolioName is the name of the portfolio.
   * @return true if it exists.
   */
  public Boolean ifPortfolioExists(String portfolioName) {

    File f = new File("./src/model/portfolios/");

    File[] matchingFiles = f.listFiles(new FilenameFilter() {
      public boolean accept(File dir, String name) {
        return name.startsWith(portfolioName) && name.endsWith("txt");
      }
    });
    return matchingFiles != null;
  }
}
