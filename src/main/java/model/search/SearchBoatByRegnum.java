package model.search;

import java.util.Iterator;
import java.util.Map;
import model.domain.Boat;
import model.domain.Member;

/**
 * Checks if Boat regnum meets search criteria.
 */
public class SearchBoatByRegnum implements SearchInterface {

  private String regnum;

  public SearchBoatByRegnum(String regnum) {
    this.regnum = regnum;
  }

  @Override
  public boolean isFound(Member m) {
    Iterator<Map.Entry<String, Boat>> map = m.boatsIterator();
    while (map.hasNext()) {
      if (map.next().getKey().equals(regnum)) {
        return true;
      }
    }

    return false;
  }

}
