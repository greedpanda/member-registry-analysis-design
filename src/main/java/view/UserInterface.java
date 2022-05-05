package view;

import java.util.Iterator;
import java.util.Map;
import model.domain.Member;

/**
 * View Interface.
 */
public interface UserInterface {

  UserOption getUserOption(UserOption menu);

  String readUserInput();

  void displayMessage(String message);

  void boatTypeMenu();

  void displayVerboseMembersInfo(Iterator<Map.Entry<String, Member>> iterator);

  void displayCompactMembersInfo(Iterator<Map.Entry<String, Member>> iterator);

  void displaySpecificMemberInfo(Iterator<Map.Entry<String, Member>> iterator, String str);

}
