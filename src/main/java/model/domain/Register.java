package model.domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import model.authentication.AuthenticatorInterface;
import model.search.SearchInterface;

/**
 * Register class, stores members and has methods to access members and boats of
 * each.
 */
public class Register {

  private Map<String, Member> members;
  private boolean isAuthenticated;

  public void setIsAuthenticated(AuthenticatorInterface auth) {
    this.isAuthenticated = auth.getAuthStatus();
  }

  // 1 initial char name, 1 initial char last name, 4 random numbers
  private String generateMemberId(String name) {
    String[] strings = name.split(" ", 0);
    StringBuilder memberId = new StringBuilder();
    memberId.append(strings[0].charAt(0) + String.valueOf(strings[strings.length - 1].charAt(0)));

    for (int i = 0; i <= 3; i++) {
      memberId.append(new Random().nextInt(10));
    }
    return memberId.toString().toLowerCase();
  }

  /**
   * Add new member.
   * Returns false in case this operation cannot be executed,
   * because user is not authorized
   */
  public boolean addMember(String name, String pnr) {
    if (this.isAuthenticated) {
      while (true) {
        String tempId = generateMemberId(name);
        if (members.get(tempId) == null) {
          members.put(tempId, new Member(name, pnr, tempId));
          break;
        }
      }
      return true;
    } 
    return false;
  }

  /**
   * Delete member.
   * Returns false in case this operation cannot be executed,
   * because user is not authorized
   */
  public boolean delMember(String memberId) {
    if (this.isAuthenticated) {
      members.remove(memberId);
      return true;
    }
    return false;
  }

  /**
   * Edit member name.
   */
  public void editMemberName(String memberId, String name) {
    members.get(memberId).setName(name);
  }

  /**
   * Edit member personal number (pnr).
   */
  public void editMemberPnr(String memberId, String pnr) {
    members.get(memberId).setPnr(pnr);
  }

  /**
   * Set member info.
   */
  public boolean setMembers(Map<String, Member> clubMembers) {
    boolean flag = false;
    if (clubMembers != null) {
      this.members = clubMembers;
      flag = true;
    }

    return flag;
  }

  /**
   * Member iterator.
   */
  public Iterator<Map.Entry<String, Member>> membersIterator() {
    return this.members.entrySet().iterator();
  }

  /**
   * Add a boat to the respective member.
   */
  public void addMemberBoat(String memberId, String regnum, double length, String type) {
    members.get(memberId).addBoat(regnum, length, type);
  }

  /**
   * Delete a boat of a respective member.
   */
  public void delMemberBoat(String memberId, String regnum) {
    members.get(memberId).delBoat(regnum);
  }

  /**
   * Edits a boat type of a respective member.
   */
  public void editMemberBoatType(String memberId, String regnum, String type) {
    members.get(memberId).editBoatType(regnum, type);
  }

  /**
   * Edits a boat length of a respective member.
   */
  public void editMemberBoatLength(String memberId, String regnum, double length) {
    members.get(memberId).editBoatLength(regnum, length);
  }

  /**
   * Search in specific members, that are passed into the method.
   * Member iterator based on search results of SearchInterface.
   */
  public Iterator<Map.Entry<String, Member>> searchMember(SearchInterface search,
      Iterator<Map.Entry<String, Member>> map) {
    Map<String, Member> foundMembers = new HashMap<>();
    while (map.hasNext()) {
      Member m = map.next().getValue();
      if (search.isFound(m)) {
        foundMembers.put(m.getMid(), m);
      }
    }
    if (foundMembers.isEmpty()) {
      return null;
    }
    return foundMembers.entrySet().iterator();
  }

  /**
   * Overloaded search for searching in all members of the register.
   * Member iterator based on search results of SearchInterface.
   */
  public Iterator<Map.Entry<String, Member>> searchMember(SearchInterface search) {
    Map<String, Member> foundMembers = new HashMap<>();
    for (Member m : members.values()) {
      if (search.isFound(m)) {
        foundMembers.put(m.getMid(), m);
      }
    }
    if (foundMembers.isEmpty()) {
      return null;
    }
    return foundMembers.entrySet().iterator();
  }

}
