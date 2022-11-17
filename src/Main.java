import java.io.IOException;

import view.UserInputMain;

/**
 * Main class which asks for user inputs.
 */
public class Main {

  /**
   * The main method that triggers the input screens for the user.
   */
  public static void main(String[] args) throws IOException {
    UserInputMain ui = new UserInputMain();
    ui.userInput();


  }
}
