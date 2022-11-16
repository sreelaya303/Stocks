package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that implements all the methods of AlphaVantageApi.
 */
public class AlphaVantageApi implements ApiInterface {

  private static String apiKey = "W0M1JOKC82EZEQA8";

  /**
   * Gets the stock price.
   *
   * @param ticker   name of the stock.
   * @param required date of when the price is required.
   * @return price of the stock.
   * @throws IOException if the input is invalid.
   */
  public float getStockPrice(String ticker, LocalDate required) throws IOException {
    // Make a cash directory if it does not exist
    makeDirectory();

    // Check cash for stock price info
    String filename = checkCash(ticker);

    // If stock info is not in cash then make an api request
    if (filename.equals("")) {
      filename = apiRequest(ticker);
    }
    // validate cash
    filename = validateCash(filename, required, ticker);
    // read data
    String res = readFile(filename, required);
    assert res != null;
    try {
      return Float.parseFloat(res);
    } catch (Exception e) {
      return -1;
    }
  }

  /**
   * Check if the stock name is valid.
   *
   * @param ticker name of the stock.
   * @return true if it is valid.
   */
  @Override
  public boolean checkTicker(String ticker) {
    String filename = checkCash(ticker);
    if (!Objects.equals(filename, "")) {
      return true;
    }
    String res = apiRequest(ticker);
    return !Objects.equals(res, "");
  }

  /**
   * Checks if the date is valid or not.
   *
   * @param
   * @return true if the date is not in the future.
   */
//  @Override
//  public boolean checkDate(LocalDate required) {
//    // can't get stock prices in the future.
//    LocalDate today = LocalDate.now();
//    return today.compareTo(required) >= 0;
//  }

  private void makeDirectory() {
    File dir = new File("./src/model/cash");
    dir.mkdirs();
  }

  private String validateCash(String filename, LocalDate required, String ticker) {

    String[] tokens = filename.split("-", 2);
    LocalDate available = LocalDate.parse(tokens[1].replace(".txt", ""));


    // if cash is valid for a given date
    if (available.compareTo(required) > 0) {
      return filename;
    }
    // invalidate cash
    File myObj = new File(filename);
    if (!myObj.delete()) {
      System.out.println("Failed to invalidate cash");
    }

    return apiRequest(ticker);
  }

  private String readFile(String filename, LocalDate date) throws IOException {
    String temp = date + "(.*)" + "(\r\n|\r|\n)";
    Pattern pattern = Pattern.compile(temp);
    Matcher matcher = pattern.matcher(fromFile(filename));
    if (matcher.find()) {
      String match = matcher.group();
      String[] tokens = match.split(",");
      return tokens[1];
    }

    return null;
  }

  private static CharSequence fromFile(String filename) throws IOException {
    FileInputStream fis = new FileInputStream(filename);
    FileChannel fc = fis.getChannel();

    // Create a read-only CharBuffer on the file
    ByteBuffer bbuf = fc.map(FileChannel.MapMode.READ_ONLY, 0, (int) fc.size());
    return StandardCharsets.ISO_8859_1.newDecoder().decode(bbuf);
  }


  private String checkCash(String ticker) {

    File f = new File("./src/model/cash/");

    File[] matchingFiles = f.listFiles((dir, name)
            -> name.startsWith(ticker) && name.endsWith("txt"));
    assert matchingFiles != null;
    if (matchingFiles.length == 0) {
      return "";
    }
    return String.valueOf(matchingFiles[0]);
  }

  private String apiRequest(String stockSymbol) {
    URL url = null;
    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }

    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }

    // invalid ticker
    if (output.toString().contains("Error Message")) {
      return "";
    }

    LocalDate myObj = LocalDate.now();
    String filename = "./src/model/cash/" + stockSymbol + "-" + myObj + ".txt";

    File file = new File(filename);
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.append(output);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return filename;
  }


}

