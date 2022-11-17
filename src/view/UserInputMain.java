package view;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main view for the user input.
 */
public class UserInputMain {

  /**
   * Shows the first set of options to the user and directs to other classes based on the input.
   *
   * @throws IOException if the user input is invalid.
   */
  public void userInput() throws IOException {

    final Scanner myObj = new Scanner(System.in);
    Options.START.print();

    boolean repeat = false;
    do {
      Options.CREATE_LOAD.print();
      String choice = myObj.nextLine().replace(" ", "");
      switch (choice) {
        case "1":
          CreatePortfolio createPortfolio = new CreatePortfolio();
          createPortfolio.userInput();
          repeat = true;
          createPortfolio = null;
          break;
        case "2":
          LoadPortfolio loadPortfolio = new LoadPortfolio();
          loadPortfolio.userInput();
          repeat = true;
          loadPortfolio = null;
          break;
        case "EXIT":
          // TO-DO
          Options.EXIT.print();
          break;
        default:
          Options.WRONG_OPTION.print();
          Options.CREATE_LOAD.print();
          repeat = true;
      }
    }
    while (repeat);
  }

}
