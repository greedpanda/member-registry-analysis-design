package model.search;

import model.domain.Member;

/**
 * Search interface.
 */
public interface SearchInterface {
  boolean isFound(Member m);
}
