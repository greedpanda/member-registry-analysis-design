package model.domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Member class, stores boats and has methods to access boats of each member.
 */
public class Member {

  private String name;
  private String pnr;
  private String mid;
  private Map<String, Boat> boats;

  /**
   * In place for persistence mapping purposes.
   */
  public Member() {
    super();
  }

  /**
   * Member constructor.
   */
  Member(String name, String pnr, String mid) {
    this.name = name;
    this.pnr = pnr;
    this.mid = mid;
    this.boats = new HashMap<>();
  }

  public String getName() {
    return this.name;
  }

  public String getPnr() {
    return this.pnr;
  }

  public String getMid() {
    return this.mid;
  }

  void setName(String name) {
    this.name = name;
  }

  void setPnr(String pnr) {
    this.pnr = pnr;
  }

  /**
   * Returns iterator through members.
   */
  public Iterator<Map.Entry<String, Boat>> boatsIterator() {
    return this.boats.entrySet().iterator();
  }

  /**
   * In place, and public, for persistence mapping purposes.
   */
  public Map<String, Boat> getBoats() {
    return this.boats;
  }

  /**
   * get number of boats.
   */
  public int numberOfBoats() {
    return boats.size();
  }

  /**
   * add new Boat to member boats.
   */
  void addBoat(String regnum, double length, String type) {
    boats.put(regnum, new Boat(regnum, length, type));
  }

  /**
   * delete boat.
   */
  void delBoat(String regnum) {
    boats.remove(regnum);
  }

  /**
   * edit boat.
   */
  void editBoatType(String regnum, String type) {
    boats.get(regnum).setBoatType(type);
  }

  /**
   * edit boat.
   */
  void editBoatLength(String regnum, double length) {
    boats.get(regnum).setBoatLength(length);
  }

}
