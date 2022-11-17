No major design changes were made.

Most of our design follows the MVC framework and was majorly guided by it.

The views folder mainly contains the logic to take user input and display the output after
carrying out the necessary operations. It also has a significant amount of input validation code
to make sure a lot of edge-cases and malformed user inputs are handled.

The model is responsible for most of the data management, its responsibilities include making
API request, cashing the requests to hence avoid unnecessary network requests, writing and reading
portfolios to disk and performing the composition analysis of the portfolio.

The controller acts as the glue between the model and the view, often acting as a abstraction
between the two. It helps create a nice code boundary significantly limiting the amount of direct
interaction the model and view have.

One area where we chose to break MVC was in reading a portfolio from a file and in performing
analysis. Having these go through the controller just felt like passing objects for the sake of
following MVC. Hence this is the one part where we chose to drift away a little from mvc.

