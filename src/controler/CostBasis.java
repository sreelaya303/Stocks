package controler;

public class CostBasis {
  public CostBasis() {
  }

  public float getCostBasis(Portfolio portfolio) {
    System.out.println(portfolio.getCommission());
    return 0.0F;
  }
}