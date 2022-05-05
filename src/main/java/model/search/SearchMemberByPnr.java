package model.search;

import model.domain.Member;

/**
 * Checks if Member pnr meets search criteria.
 */
public class SearchMemberByPnr implements SearchInterface {

  private String pnr;

  public SearchMemberByPnr(String pnr) {
    this.pnr = pnr;
  }

  @Override
  public boolean isFound(Member m) {
    return m.getPnr().equals(this.pnr);
  }

}
