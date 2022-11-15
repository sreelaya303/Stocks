package view;

import java.util.Scanner;

import controler.ExamineComposition;
import controler.Portfolio;
import model.ReadWriteToFile;

/**
 * Loads the portfolio from the file.
 */
public class LoadPortfolio {
  /**
   * Takes the input from the user.
   */
  public void userInput() {
    Scanner myObj = new Scanner(System.in);
    Portfolio ps = null;

    Options.LOAD_START.print();
    boolean repeat;
    do {
      repeat = false;
      Options.LOAD.print();
      String file = myObj.nextLine();
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
          continue;
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
          // calculate value on a certain date.
          break;
        case "3":
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
