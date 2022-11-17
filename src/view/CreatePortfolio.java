package view;

import java.util.Scanner;

import controler.Portfolio;

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
    inflexiablePortfolio inf = new inflexiablePortfolio();
    final Scanner myObj = new Scanner(System.in);

    Options.CREAT_START.print();
    Options.TYPE_OF_PORTFOLIO.print();
    String typeOfPortfolio = myObj.nextLine();
    switch (typeOfPortfolio) {
      case "1":
        portfolio.setFlexible(false);
        inf.userInput(portfolio);
        break;
      case "2":
        portfolio.setFlexible(true);
        inf.userInput(portfolio);
        break;
      case "EXIT":
        Options.EXIT.print();
        break;
      default:
        Options.WRONG_OPTION.print();
        Options.TYPE_OF_PORTFOLIO.print();
    }



  }
}
