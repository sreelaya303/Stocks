package view;

import java.time.LocalDate;
import java.util.Scanner;

import controler.ExamineComposition;
import controler.Portfolio;
import controler.PortfolioPriceOnDate;
import model.ReadWriteToFile;

/**
 * Loads the portfolio from the file.
 */
public class LoadPortfolio {
  /**
   * Takes the input from the user.
   */
  String file = "";
  public void userInput() {
    Scanner myObj = new Scanner(System.in);
    Portfolio ps = null;

    Options.LOAD_START.print();
    boolean repeat;
    do {
      repeat = false;
      Options.LOAD.print();
      file = myObj.nextLine();
      ReadWriteToFile reader = new ReadWriteToFile();
      if (!reader.ifPortfolioExists(file)) {
        Options.INVALID_FILE_NAME.print();
        repeat = true;
      } else {
        try {
          ps = reader.readFile(file);
        } catch (Exception e) {
          Options.INVALID_FILE_NAME.print();
          repeat = true;
        }

      }

    } while (repeat);

    Options.PORTFOLIO_OPTIONS.print();
    String choice = myObj.nextLine();

    repeat = true;
    do {
      switch (choice) {
        case "1":
          // analyse composition.
          ExamineComposition comp = new ExamineComposition();
          comp.analysePortfolio(ps);
          repeat = false;
          break;
        case "2":
          PortfolioPriceOnDate price = new PortfolioPriceOnDate();
          Options.PORTFOLIO_PRICE_ON_DATE.print();
          String date = myObj.nextLine().replace(" ", "");
          LocalDate purchaseDate;
          try {
            purchaseDate = LocalDate.parse(date);
          } catch (Exception e) {
            repeat = true;
            Options.INVALID_STOCK_PURCHASE_DATE.print();
            break;
          }
          LocalDate today = LocalDate.now();
          if (today.compareTo(purchaseDate) <= 0) {
            Options.INVALID_STOCK_PURCHASE_DATE.print();
            repeat = true;
            break;
          } else {
            try {
              price.getPortfolioPriceOnDate(ps, purchaseDate);
              repeat = false;
              break;
            } catch (Exception e) {
              repeat = true;
              Options.COULD_NOT_GET_STOCK_PRICE_FROM_DATE.print();
              break;
            }
          }
        case "3":
          if(!ps.getFlexible()){
            Options.NO_TRANS_INFLEXIBLE.print();
            repeat = false;
            break;
          }
          Options.STOCK_TICKER.print();
          String name = myObj.nextLine();
          Options.STOCK_BUY_SELL.print();
          String buySell = myObj.nextLine();
          String buyOrSell = null;
          switch (buySell){
            case "1":
              buyOrSell = "buy";
              break;
            case "2":
              buyOrSell = "sell";
              break;
            default:
              Options.WRONG_OPTION.print();
              break;
          }
          Options.STOCK_QUANTITY.print();
          double quantity = myObj.nextDouble();

          Options.STOCK_BUY_SELL_DATE.print();
          String d = myObj.next();
          LocalDate transactionDate;
          try {
            transactionDate = LocalDate.parse(d.replace(" ", ""));
          } catch (Exception e) {
            Options.INVALID_STOCK_PURCHASE_DATE.print();
            break;
          }
          ReadWriteToFile rf = new ReadWriteToFile();
          rf.buySellStocks(file, ps, name, buyOrSell, quantity, transactionDate);
          repeat = false;
          break;
        case "4":
          repeat = false;
          break;
        case "EXIT":
          // TO-DO
          Options.EXIT.print();
          break;
        default:
          Options.WRONG_OPTION.print();
          Options.CREATE_LOAD.print();
      }
    } while (repeat);

  }
}
