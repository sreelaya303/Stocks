package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
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
  public Portfolio readFile(String filePath) {
    Portfolio pnew = new Portfolio();
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(filePath));
      String line = reader.readLine();
      File f = new File(filePath);
      pnew.setName((f.getName()).replaceFirst("[.][^.]+$", ""));
      pnew.setDateOfCreation(LocalDate.parse(line));
      line = reader.readLine();
      while (line != null) {
        String[] sa = line.split("\\s*,\\s*");
        Stock ps = new Stock(sa[0], Long.parseLong(sa[1]), Float.parseFloat(sa[2]));
        pnew.addStocks(ps);
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
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

//    try {
//      file = new FileWriter("./src/model/Portfolios/" + portfolio.getPortfolioName()
//              + ".txt");
//
//      file.write(String.valueOf(portfolio.getDateOfCreation()));
//      file.write("\n");
//      for (int i = 0; i < portfolio.getNumStocks(); i++) {
//        Stock temp = Portfolio.getStock(i);
//        file.write(temp.getStockName() + "," + temp.getStockNumber() + "," + temp.getStockPrice()
//                + "\n");
//      }
//    }
    JSONArray allStocks = new JSONArray();
    for (int i = 0; i < portfolio.getNumStocks(); i++) {
      JSONObject obj = new JSONObject();
      Stock temp = Portfolio.getStock(i);
      obj.put("Name", temp.getStockName());
      obj.put("Number", temp.getStockNumber());
      obj.put("Date", temp.getStockPrice());
      allStocks.add(obj);
    }

    try {
      file = new FileWriter("./src/model/Portfolios/" + portfolio.getPortfolioName() + ".txt");
      file.write(allStocks.toJSONString());
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
