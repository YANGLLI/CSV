package model.alignpublic;

import java.io.Serializable;

public class MultipleValueAggregatedData implements Serializable {
  private String analyticTerm;
  private String analyticKey;
  private int analyticValue;

  public MultipleValueAggregatedData(String analyticKey, int analyticValue) {
    this.analyticKey = analyticKey;
    this.analyticValue = analyticValue;
  }

  public MultipleValueAggregatedData() { }

  public String getAnalyticTerm() {
    return analyticTerm;
  }

  public void setAnalyticTerm(String analyticTerm) {
    this.analyticTerm = analyticTerm;
  }

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
