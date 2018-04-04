package model.alignpublic;

public class DataCount {
  private String dataKey;
  private int dataValue;

  public DataCount(String dataKey, int dataValue) {
    this.dataKey = dataKey;
    this.dataValue = dataValue;
  }

  public String getDataKey() {
    return dataKey;
  }

  public void setDataKey(String dataKey) {
    this.dataKey = dataKey;
  }

  public int getDataValue() {
    return dataValue;
  }

  public void setDataValue(int dataValue) {
    this.dataValue = dataValue;
  }
}
