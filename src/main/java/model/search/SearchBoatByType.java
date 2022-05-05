package model.search;

import java.util.Iterator;
import java.util.Map;
import model.domain.Boat;
import model.domain.Member;

/**
 * Checks if Boat type meets search criteria.
 */
public class SearchBoatByType implements SearchInterface {

  private String boatType;

  public SearchBoatByType(String boatType) {
    this.boatType = boatType;
  }

  @Override
  public boolean isFound(Member m) {
    Iterator<Map.Entry<String, Boat>> map = m.boatsIterator();
    while (map.hasNext()) {
      if (map.next().getValue().getType().equals(boatType)) {
        return true;
      }
    }

    return false;
  }
  
}
