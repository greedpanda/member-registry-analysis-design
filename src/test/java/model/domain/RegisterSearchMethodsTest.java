package model.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;
import model.domain.Boat.BoatType;
import model.search.SearchBoatByRegnum;
import model.search.SearchBoatByType;
import model.search.SearchMemberById;
import model.search.SearchMemberByName;
import model.search.SearchMemberByPnr;
import org.junit.jupiter.api.Test;

/**
 * Test search Register functionality.
 */
public class RegisterSearchMethodsTest {

  private Register sut;

  public RegisterSearchMethodsTest() {

    Map<String, Member> clubMembers = new HashMap<>();
    clubMembers.put("js1234", new Member("John Smith", "123456789345", "js1234"));
    clubMembers.put("js3412", new Member("Jane Smith", "123456789567", "js3412"));
    sut = new Register();
    sut.setMembers(clubMembers);
    sut.addMemberBoat("js1234", "123456", 5.0, BoatType.convertToValue(0));
    sut.addMemberBoat("js1234", "123457", 6.0, BoatType.convertToValue(1));
    sut.addMemberBoat("js3412", "123458", 5.0, BoatType.convertToValue(2));
    sut.addMemberBoat("js3412", "123459", 6.0, BoatType.convertToValue(1));
  }

  /**
   * Test of search methods.
   * Search with iterator parameter is tested on the all members of the register,
   * but in pratice it is used for search in specific subset of members.
   */
  @Test
  public void testSearchMember() {
    assertNull(sut.searchMember(new SearchBoatByType(BoatType.convertToValue(3)), sut.membersIterator()));
    assertNotNull(sut.searchMember(new SearchBoatByType(BoatType.convertToValue(0)), sut.membersIterator()));
    assertNull(sut.searchMember(new SearchBoatByRegnum("456789"), sut.membersIterator()));
    assertNotNull(sut.searchMember(new SearchBoatByRegnum("123456"), sut.membersIterator()));
    assertNotNull(sut.searchMember(new SearchBoatByRegnum("123459"), sut.membersIterator()));
    assertNull(sut.searchMember(new SearchMemberByName("ka"), sut.membersIterator()));
    assertNotNull(sut.searchMember(new SearchMemberByName("j"), sut.membersIterator()));
    assertNotNull(sut.searchMember(new SearchMemberByName("jane s"), sut.membersIterator()));
    assertNull(sut.searchMember(new SearchMemberByPnr("888888888888"), sut.membersIterator()));
    assertNotNull(sut.searchMember(new SearchMemberByPnr("123456789567"), sut.membersIterator()));
    assertNull(sut.searchMember(new SearchMemberById("km5555"), sut.membersIterator()));
    assertNotNull(sut.searchMember(new SearchMemberById("js3412"), sut.membersIterator()));

  }

}
