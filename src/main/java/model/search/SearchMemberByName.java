package model.search;

import model.domain.Member;

/**
 * Checks if Member name meets search criteria.
 */
public class SearchMemberByName implements SearchInterface {

  private String name;

  public SearchMemberByName(String name) {
    this.name = name;

  }

  @Override
  public boolean isFound(Member m) {
    return m.getName().toLowerCase().contains(this.name.toLowerCase());
  }

}
