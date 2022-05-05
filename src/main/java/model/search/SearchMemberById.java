package model.search;

import model.domain.Member;

/**
 * Checks if Member id meets search criteria.
 */
public class SearchMemberById implements SearchInterface {

  private String memberId;

  public SearchMemberById(String memberId) {
    this.memberId = memberId;

  }

  @Override
  public boolean isFound(Member m) {
    return m.getMid().equals(this.memberId);
  }

}
