package view;

import controler.Portfolio;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * A class that creates a new portfolio.
 */
public class CreatePortfolio {

  /**
   * Takes input from the user.
   */
  public void userInput() {
    Portfolio portfolio = new Portfolio();
    final StockInput stockInput = new StockInput();
    final UserInputMain userInputMain = new UserInputMain();
    final Scanner myObj = new Scanner(System.in);

    Options.CREAT_START.print();

    Options.PORTFOLIO_NAME.print();
    String name = myObj.nextLine();
    portfolio.setName(name);
    portfolio.setDateOfCreation(LocalDate.now());

    boolean repeat;
    do {
      repeat = false;
      Options.CREATE.print();
      String choice = myObj.nextLine();
      switch (choice) {
        case "1":
          portfolio.addStocks(stockInput.userInput());
          repeat = true;
          break;
        case "2":
          if (portfolio.getNumStocks() == 0) {
            Options.EMPTY_PORTFOLIO.print();
            Options.CREATE_LOAD.print();
            repeat = true;
            break;
          }
          portfolio.saveToDisk();
          Options.CREATE_SUCCESS.print();
          repeat = false;
          break;
        case "EXIT":
          // TO-DO
          Options.EXIT.print();
          repeat = false;
          break;
        default:
          Options.WRONG_OPTION.print();
          Options.CREATE_LOAD.print();
          repeat = true;
      }
    } while (repeat);

  }
}
