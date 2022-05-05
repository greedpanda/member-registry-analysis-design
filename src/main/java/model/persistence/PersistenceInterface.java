package model.persistence;

import java.util.Iterator;
import java.util.Map;
import model.domain.Member;


/**
 * Interface for persistence manager.
 */
public interface PersistenceInterface {

  /**
   * Load from file.
   */
  Map<String, Member> loadData();

  /**
   * Save to file.
   */
  boolean saveData(Iterator<Map.Entry<String, Member>> map);

}
