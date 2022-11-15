package controler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.ReadWriteToFile;

/**
 * A class that has all the model methods for portfolio.
 */
public class Portfolio {

  static List<Stock> myStocks;
  LocalDate dateOfCreation;

  public Portfolio(){
    myStocks = new ArrayList<>();
    myStocks.clear();
  }


  public Boolean getFlexible() {
    return isFlexible;
  }

  public void setFlexible(Boolean flexible) {
    isFlexible = flexible;
  }

  private Boolean isFlexible;

  public List<Stock> getMyStocks() {
    return myStocks;
  }

  /**
   * Gets the stock at that index.
   *
   * @param i the specific index.
   * @return the object portfolio stock.
   * @throws IllegalArgumentException when the given index doesn't exist.
   */
  public static Stock getStock(int i) throws IllegalArgumentException {
    if (i >= myStocks.size()) {
      throw new IllegalArgumentException("Stock at this index does not exist");
    }
    return myStocks.get(i);
  }

  /**
   * Gets the total number of stocks in the portfolio.
   *
   * @return the size of the myStocks list.
   */
  public int getNumStocks() {
    return myStocks.size();
  }


  /**
   * Gets the date the portfolio was created.
   *
   * @return the date of creation of the portfolio.
   */
  public LocalDate getDateOfCreation() {
    return dateOfCreation;
  }

  private String name;

  /**
   * Sets the dateOfCreation for the portfolio.
   *
   * @param dateOfCreation of the portfolio.
   */
  public void setDateOfCreation(LocalDate dateOfCreation) {
    this.dateOfCreation = dateOfCreation;
  }

  /**
   * Sets the name of the portfolio.
   *
   * @param name name of the portfolio.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the name of the portfolio.
   *
   * @return the name of the portfolio.
   */
  public String getPortfolioName() {
    return this.name;
  }

  /**
   * Adds new stocks to the portfolio.
   *
   * @param ps is a stock.
   */
  public void addStocks(Stock ps) {
    myStocks.add(ps);
  }

  /**
   * Saves the portfolio as a file.
   */
  public void saveToDisk() {
    ReadWriteToFile fs = new ReadWriteToFile();
    fs.writeToFile(this);
  }

}
