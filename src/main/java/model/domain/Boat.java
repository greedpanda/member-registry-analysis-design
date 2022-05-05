package model.domain;

/**
 * Boat class, with relative setters and getters.
 */
public class Boat {

  /**
   * Enum with accepted boat types.
   */
  public enum BoatType {
    SAIL("sailboat"), MOTOR("motorsailer"), KAYAK("kayak/canoe"), OTHER("other");

    public final String str;

    BoatType(String str) {
      this.str = str;
    }

    public static String convertToValue(int value) {
      return values()[value].str;
    }
  }

  private String regnum;
  private double length;
  private String type;

  /**
   * In place for persistence mapping purposes.
   */
  public Boat() {
    super();
  }

  /**
   * Boat constructor.
   */
  Boat(String regnum, double length, String type) {
    this.regnum = regnum;
    this.length = length;
    this.type = type;
  }

  public String getRegnum() {
    return this.regnum;
  }

  public double getLength() {
    return this.length;
  }

  public String getType() {
    return this.type;
  }

  /**
   * Edits boat type.
   */
  void setBoatType(String type) {
    this.type = type;
  }

  /**
   * Edits boat length.
   */
  void setBoatLength(double length) {
    this.length = length;
  }
}
