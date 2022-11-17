package view;

import java.time.LocalDate;
import java.util.Scanner;

import controler.Portfolio;

public class inflexiablePortfolio {

  public void userInput(Portfolio portfolio) {
    Options.PORTFOLIO_NAME.print();
    Scanner myObj = new Scanner(System.in);
    StockInput stockInput = new StockInput();
    String name = myObj.nextLine();
    portfolio.setName(name);
    portfolio.setDateOfCreation(LocalDate.now());

    if (portfolio.getFlexible()) {
      Options.GET_FEES.print();
      String cost = myObj.nextLine();
      portfolio.setCommission(Float.parseFloat(cost));
    }

    boolean repeat;
    do {
      repeat = false;
      Options.CREATE.print();
      switch (myObj.nextLine()) {
        case "1":
          portfolio.addStocks(stockInput.userInput());
          repeat = true;
          break;
        case "2":
          if (portfolio.getNumStocks() == 0) {
            Options.EMPTY_PORTFOLIO.print();
            Options.CREATE.print();
            repeat = true;
          } else {
            portfolio.saveToDisk();
            Options.CREATE_SUCCESS.print();
            portfolio = null;
            repeat = false;
          }
          break;
        case "EXIT":
          Options.EXIT.print();
          repeat = false;
          break;
        default:
          Options.WRONG_OPTION.print();
          Options.CREATE.print();
          repeat = true;
      }
    } while(repeat);

  }
}
