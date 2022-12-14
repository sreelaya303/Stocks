package view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import controler.CostBasis;
import controler.ExamineComposition;
import controler.Portfolio;
import controler.PortfolioPriceOnDate;
import controler.ShowGraph;
import model.ReadWriteToFile;

/**
 * Loads the portfolio from the file given its path.
 */
public class LoadPortfolio {
  String file = "";

  /**
   * Takes the file path from the user
   * and proceeds to give multiple options on the operations that can be performed on it.
   *
   * @throws IOException when user input is invalid.
   */
  public void userInput() throws IOException {
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

    }
    while (repeat);

    Options.PORTFOLIO_OPTIONS.print();
    String choice = myObj.nextLine();

    repeat = true;
    do {
      switch (choice) {
        case "1":
          ExamineComposition comp = new ExamineComposition();
          comp.analysePortfolio(ps);
          repeat = false;
          break;
        case "2":
          Options.SHOW_GRAPH.print();
          String s = myObj.next();
          switch (s) {
            case "1":
              Options.PORTFOLIO_FROM.print();
              String pf = myObj.next();
              Options.PORTFOLIO_TO.print();
              String pt = myObj.next();
              LocalDate fromDate;
              LocalDate toDate;
              try {
                fromDate = LocalDate.parse(pf.replace(" ", ""));
                toDate = LocalDate.parse(pt.replace(" ", ""));
              } catch (Exception e) {
                Options.INVALID_STOCK_PURCHASE_DATE.print();
                break;
              }
              ShowGraph sg = new ShowGraph(ps, fromDate, toDate);
              sg.showGraph();
              repeat = false;
              break;
            case "2":
              PortfolioPriceOnDate price = new PortfolioPriceOnDate();
              Options.PORTFOLIO_PRICE_ON_DATE.print();
              String date = myObj.next().replace(" ", "");
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
                  float temp = price.getPortfolioPriceOnDate(ps, purchaseDate);
                  System.out.println("Price of portfolio on " + date + " was " + temp);
                  repeat = false;
                  break;
                } catch (Exception e) {
                  repeat = true;
                  Options.COULD_NOT_GET_STOCK_PRICE_FROM_DATE.print();
                  break;
                }
              }
            default:
              break;
          }
          break;
        case "3":
          if (!ps.getFlexible()) {
            Options.NO_TRANS_INFLEXIBLE.print();
            repeat = false;
            break;
          }
          Options.STOCK_TICKER.print();
          String name = myObj.next();
          Options.STOCK_BUY_SELL.print();
          String buySell = myObj.next();
          String buyOrSell = null;
          switch (buySell) {
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
          rf.buySellStocks(ps, name, buyOrSell, quantity, transactionDate);
          repeat = false;
          break;
        case "4":
          CostBasis cb = new CostBasis();
          System.out.println(cb.getCostBasis(ps));
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
    }
    while (repeat);

  }
}
