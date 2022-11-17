package view;

/**
 * Enum for different options given during user interaction.
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

  STOCK_BUY_SELL_DATE("Enter the date this transaction has to be done on (YYYY-MM-DD):"),
  STOCK_PRICE_OPTIONS("Press (1) to add stock entry price,"
          + " Press (2) to add stock purchase date"),
  PORTFOLIO_NAME("Enter the name of your portfolio:"),

  EMPTY_PORTFOLIO("Cannot save empty portfolio, add stocks to your portfolio!"),

  INVALID_TICKER("The stock ticker is invalid, please try again!"),

  STOCK_PRICE_NEGATIVE("The stock price cannot be negative, please try again."),

  STOCK_PRICE_NAN("The stock price needs to be a number"),

  INVALID_STOCK_PURCHASE_DATE("The date is invalid, Please try again"),

  COULD_NOT_GET_STOCK_PRICE_FROM_DATE("Could not get stock price from date, try again!"),

  STOCK_PRICE_UNAVAILABLE("Stock info for this day is not available, try entering it "
          + "manually"),
  STOCK_QUANTITY("Enter the stock quantity"),

  INVALID_STOCK_QUANTITY("The stock quantity should be a number greater than zero."),

  INVALID_FILE_NAME("Invalid file name"),

  PORTFOLIO_OPTIONS("(1) to analyse composition, "
          + "(2) to calculate portfolio value, "
          + "(3) to purchase or sell stocks in this portfolio, "
          + "(4) to go back to main menu."),

  STOCK_BUY_SELL("Press (1) to buy new stocks, Press (2) to sell existing stocks"),

  NO_TRANS_INFLEXIBLE("No transactions can be made on inflexible portfolios."),
  SHOW_GRAPH("Press (1) to show the value of the portfolio over a certain period as a graph, "
          + "(2) to show the value of the portfolio on a particular date"),
  PORTFOLIO_FROM("Enter the date from which the portfolio value has to be shown (YYYY-MM-DD):"),
  PORTFOLIO_TO("Enter the date until which the portfolio value has to be shown (YYYY-MM-DD):"),
  PORTFOLIO_PRICE_ON_DATE("Enter the date you want the portfolio value on (YYYY-MM-DD):");


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
