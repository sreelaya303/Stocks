package view;

import java.util.Scanner;

/**
 * Main input for user.
 */
public class UserInputMain {

  /**
   * Takes user input.
   */
  public void userInput() {
    final CreatePortfolio createPortfolio = new CreatePortfolio();
    final LoadPortfolio loadPortfolio = new LoadPortfolio();
    final Scanner myObj = new Scanner(System.in);
    Options.START.print();

    boolean repeat = false;
    do {
      Options.CREATE_LOAD.print();
      String choice = myObj.nextLine().replace(" ", "");
      switch (choice) {
        case "1":
          createPortfolio.userInput();
          repeat = true;
          break;
        case "2":
          loadPortfolio.userInput();
          repeat = true;
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
    } while (repeat);
  }

}
