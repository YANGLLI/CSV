package model.alignpublic;

public class SingleValueAggregatedData {
  private String analyticKey;
  private int analyticValue;

  public SingleValueAggregatedData() { }

  public String getAnalyticKey() {
    return analyticKey;
  }

  public void setAnalyticKey(String analyticKey) {
    this.analyticKey = analyticKey;
  }

  public int getAnalyticValue() {
    return analyticValue;
  }

  public void setAnalyticValue(int analyticValue) {
    this.analyticValue = analyticValue;
  }
}