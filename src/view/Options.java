package view;

/**
 * Enum for different options given during user input.
 */
public enum Options {
  WRONG_OPTION("Sorry that was a wrong input, please try again :-)"),
  CREATE_LOAD("Press (1) if you want to create a new portfolio,"
          + " Press (2) if you want to load an existing portfolio, Press EXIT to quit."),
  EXIT("Thank you for using stock analyzer 3000"),
  START("Welcome to stock analyzer 3000!"),

  CREAT_START("Let's build your new portfolio"),
  TYPE_OF_PORTFOLIO("Press (1) to create an inflexible portfolio, "
          + "Press (2) to create a flexible portfolio, "
          + "Press EXIT to quit"),
  LOAD_START("Let's load your existing portfolio"),
  CREATE("Press (1) to add a new stock to your portfolio,"
          + " Press (2) to save portfolio, Press EXIT to quit"),
  LOAD("Please provide the filepath to you existing portfolio:"),
  CREATE_SUCCESS("The portfolio has been successfully created, "
          + "Please load the created file to analyse the portfolio."),
  STOCK_TICKER("Enter stock ticker: "),
  STOCK_ENTRY_PRICE("Enter the stock entry price:"),
  STOCK_PURCHASE_DATE("Enter the date the stock was purchased (YYYY-MM-DD):"),
  STOCK_PRICE_OPTIONS("Press (1) to add stock entry price,"
          + " Press (2) to add stock purchase date"),
  PORTFOLIO_NAME("Enter the name of your portfolio:"),

  EMPTY_PORTFOLIO("Cannot save empty portfolio, add stocks to your portfolio!"),

  INVALID_TICKER("The stock ticker is invalid, please try again!"),

  STOCK_PRICE_NEGATIVE("The stock price cannot be negative, please try again."),

  STOCK_PRICE_NAN("The stock price needs to be a number"),

  INVALID_STOCK_PURCHASE_DATE("The stock purchase date is invalid"),

  COULD_NOT_GET_STOCK_PRICE_FROM_DATE("Could not get stock price from date, try again!"),

  STOCK_PRICE_UNAVAILABLE("Stock info for this day is not available, try entering it "
          + "manually"),
  STOCK_QUANTITY("Enter the stock quantity"),

  INVALID_STOCK_QUANTITY("The stock quantity should be a number greater than zero."),

  INVALID_FILE_NAME("Invalid file name"),

  PORTFOLIO_OPTIONS("(1) to analyse composition, "
          + "(2) to calculate portfolio value on a given date, "
          + "(3) to go back to main menu.");

  private final String message;

  Options(String message) {
    this.message = message;
  }

  /**
   * Prints the message.
   */
  public void print() {
    System.out.println(this.message);
  }


}
