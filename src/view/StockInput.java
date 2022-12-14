package view;

import java.time.LocalDate;
import java.util.Scanner;

import controler.Stock;
import controler.StockInflexible;

/**
 * Takes input from user to show stock details.
 */
public class StockInput {
  /**
   * Takes input from the user.
   */
  public Stock userInput() {
    Stock stock = new StockInflexible();
    Scanner myObj = new Scanner(System.in);
    boolean repeat;
    do {
      // lets hope no errors.
      repeat = false;

      // read stock ticker
      Options.STOCK_TICKER.print();
      String ticker = myObj.nextLine().replace(" ", "");

      // validate stock ticker
      if (!stock.verifyStockTicker(ticker)) {
        Options.INVALID_TICKER.print();
        repeat = true;
        continue;
      } else {
        stock.setStockName(ticker);
      }

      Options.STOCK_QUANTITY.print();
      String input = myObj.nextLine();
      long quantity;
      try {
        quantity = Long.parseLong(input);
      } catch (Exception e) {
        Options.INVALID_STOCK_QUANTITY.print();
        repeat = true;
        continue;
      }

      if (quantity <= 0) {
        repeat = true;
        Options.INVALID_STOCK_QUANTITY.print();
        continue;
      } else {
        stock.setStockNumber(quantity);
      }


      Options.STOCK_PRICE_OPTIONS.print();
      String choice = myObj.nextLine().replace(" ", "");
      switch (choice) {
        case "1":
          Options.STOCK_ENTRY_PRICE.print();
          float price;
          try {
            price = Float.parseFloat(myObj.nextLine().replace(" ", ""));
          } catch (NumberFormatException e) {
            Options.STOCK_PRICE_NAN.print();
            repeat = true;
            break;
          }

          if (price < 0) {
            Options.STOCK_PRICE_NEGATIVE.print();
            repeat = true;
            break;
          } else {
            ((StockInflexible) stock).setStockPrice(price);
          }
          break;

        case "2":
          Options.STOCK_PURCHASE_DATE.print();
          String date = myObj.nextLine().replace(" ", "");
          LocalDate purchaseDate;
          try {
            purchaseDate = LocalDate.parse(date);
          } catch (Exception e) {
            Options.INVALID_STOCK_PURCHASE_DATE.print();
            repeat = true;
            break;
          }
          LocalDate today = LocalDate.now();
          float price2;
          if (today.compareTo(purchaseDate) < 0) {
            Options.INVALID_STOCK_PURCHASE_DATE.print();
            repeat = true;
            break;
          } else {
            try {
              price2 = ((StockInflexible) stock).getStockPriceOnDate(purchaseDate);
            } catch (Exception e) {
              Options.COULD_NOT_GET_STOCK_PRICE_FROM_DATE.print();
              repeat = true;
              break;
            }
            if (price2 == -1) {
              Options.STOCK_PRICE_UNAVAILABLE.print();
              repeat = true;
              break;
            } else {
              ((StockInflexible) stock).setStockPrice(price2);
            }

          }
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
    return stock;
  }
}
