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

import controler.Portfolio;
import controler.Stock;


/**
 * Writes the portfolios to a file.
 */
public class ReadWriteToFile {
  private static FileWriter file;

  /**
   * Reads the file when path is given.
   *
   * @param filePath the path of the file.
   * @return the portfolio from the path.
   */
  public Portfolio readFile(String filePath){
    Portfolio pnew = new Portfolio();
    JSONParser parser = new JSONParser();
    try {
      JSONObject port = (JSONObject) parser.parse(new FileReader(filePath));
      JSONArray stockList = (JSONArray) port.get("Stocks");
      File f = new File(filePath);
      pnew.setName((f.getName()).replaceFirst("[.][^.]+$", ""));
      pnew.setDateOfCreation(LocalDate.parse((String) port.get("Date")));
      pnew.setFlexible(f.canWrite());
      for (JSONObject obj : (Iterable<JSONObject>) stockList) {
        Stock ps = new Stock((String) obj.get("Name"), (Long) obj.get("Number"),
                (Double) obj.get("Price"));
        pnew.addStocks(ps);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pnew;
  }

  /**
   * Writes the portfolio to a path.
   *
   * @param portfolio takes the object Portfolio.
   */
  public void writeToFile(Portfolio portfolio) {
    File dir = new File("./src/model/Portfolios");
    dir.mkdirs();
    JSONObject port = new JSONObject();
    port.put("Date", String.valueOf(portfolio.getDateOfCreation()));
    JSONArray allStocks = new JSONArray();
    for (int i = 0; i < portfolio.getNumStocks(); i++) {
      JSONObject obj = new JSONObject();
      Stock temp = Portfolio.getStock(i);
      obj.put("Name", temp.getStockName());
      obj.put("Number", temp.getStockNumber());
      obj.put("Price", temp.getStockPrice());
      allStocks.add(obj);
    }
    port.put("Stocks", allStocks);

    try {
      file = new FileWriter("./src/model/Portfolios/" + portfolio.getPortfolioName() + ".txt");
      file.write(port.toJSONString());
      if(!portfolio.getFlexible()){
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
