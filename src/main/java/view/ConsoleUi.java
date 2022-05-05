package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import model.domain.Boat;
import model.domain.Member;

/**
 * Console Class.
 */
public class ConsoleUi implements UserInterface {

  private BufferedReader bufferedReader;

  /**
   * ConsoleUi Constructor, with encoding for swedish characters.
   */
  public ConsoleUi() {
    bufferedReader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
  }

  /**
   * Option Console for general User.
   */
  public UserOption getUserOption(UserOption menu) {
    if (menu.equals(UserOption.MENU_MAIN_AUTH)) {
      return mainMenuChoiceAuth();
    } else if (menu.equals(UserOption.MENU_MAIN)) {
      return mainMenuChoice();
    } else if (menu.equals(UserOption.MENU_EDIT_MEMBER)) {
      return editMemberMenuChoice();
    } else if (menu.equals(UserOption.MENU_LIST)) {
      return listMemberMenuChoice();
    } else if (menu.equals(UserOption.MENU_SEARCH)) {
      return searchMemberChoice();
    } else if (menu.equals(UserOption.MENU_EDIT_BOAT)) {
      return editMemberBoatChoice();
    }
    return null; // should never happen because EXIT or GO_BACK are returned.
  }

  /**
   * Option Console for Main Menu if logged in.
   */
  public UserOption mainMenuChoiceAuth() {
    UserOption validChoice = null;

    while (validChoice == null) {
      mainMenuAuth();
      displayMessage(Messages.ENTER_CHOICE.str);
      String c = readUserInput();

      if (c.equalsIgnoreCase("1")) {
        validChoice = UserOption.ADD_MEMBER;
      } else if (c.equalsIgnoreCase("2")) {
        validChoice = UserOption.DELETE_MEMBER;
      } else if (c.equalsIgnoreCase("3")) {
        validChoice = UserOption.EDIT_MEMBER;
      } else if (c.equalsIgnoreCase("4")) {
        validChoice = UserOption.LIST_MEMBER;
      } else if (c.equalsIgnoreCase("5")) {
        validChoice = UserOption.SHOW_MEMBER_INFO;
      } else if (c.equalsIgnoreCase("6")) {
        validChoice = UserOption.SEARCH_MEMBERS;
      } else if (c.equalsIgnoreCase("7")) {
        validChoice = UserOption.ADD_BOAT;
      } else if (c.equalsIgnoreCase("8")) {
        validChoice = UserOption.DELETE_BOAT;
      } else if (c.equalsIgnoreCase("9")) {
        validChoice = UserOption.EDIT_BOAT;
      } else if (c.equalsIgnoreCase("10")) {
        validChoice = UserOption.LOG_OUT;
      } else {
        validChoice = UserOption.EXIT;
      }
    }
    return validChoice;
  }

  /**
   * Option Console for Main Menu if not logged in.
   */
  public UserOption mainMenuChoice() {
    UserOption validChoice = null;

    while (validChoice == null) {
      mainMenu();
      displayMessage(Messages.ENTER_CHOICE.str);
      String c = readUserInput();

      if (c.equalsIgnoreCase("1")) {
        validChoice = UserOption.LIST_MEMBER;
      } else if (c.equalsIgnoreCase("2")) {
        validChoice = UserOption.SHOW_MEMBER_INFO;
      } else if (c.equalsIgnoreCase("3")) {
        validChoice = UserOption.SEARCH_MEMBERS;
      } else if (c.equalsIgnoreCase("4")) {
        validChoice = UserOption.LOG_IN;
      } else {
        validChoice = UserOption.EXIT;
      }
    }
    return validChoice;
  }

  /**
   * Option Console for Edit Member Menu.
   */
  public UserOption editMemberMenuChoice() {
    UserOption validChoice = null;

    while (validChoice == null) {
      editMemberMenu();
      displayMessage(Messages.ENTER_CHOICE.str);
      String c = readUserInput();

      if (c.equalsIgnoreCase("1")) {
        validChoice = UserOption.EDIT_MEMBER_NAME;
      } else if (c.equalsIgnoreCase("2")) {
        validChoice = UserOption.EDIT_MEMBER_PNR;
      } else if (c.equalsIgnoreCase("3")) {
        validChoice = UserOption.EDIT_MEMBER_ALL;
      } else {
        validChoice = UserOption.GO_BACK;
      }
    }
    return validChoice;
  }

  /**
   * Option Console for List Member Menu.
   */
  public UserOption listMemberMenuChoice() {
    UserOption validChoice = null;

    while (validChoice == null) {
      listMembersMenu();
      displayMessage(Messages.ENTER_CHOICE.str);
      String c = readUserInput();

      if (c.equalsIgnoreCase("1")) {
        validChoice = UserOption.LIST_VERBOSE;
      } else if (c.equalsIgnoreCase("2")) {
        validChoice = UserOption.LIST_COMPACT;
      } else {
        validChoice = UserOption.GO_BACK;
      }
    }
    return validChoice;
  }

  /**
   * Option Console for Search Member Menu.
   */
  public UserOption searchMemberChoice() {
    UserOption validChoice = null;

    while (validChoice == null) {
      searchMembersMenu();
      displayMessage(Messages.ENTER_CHOICE.str);
      String c = readUserInput();

      if (c.equalsIgnoreCase("1")) {
        validChoice = UserOption.SEARCH_ON_NAME;
      } else if (c.equalsIgnoreCase("2")) {
        validChoice = UserOption.SEARCH_ON_BOATTYPE;
      } else {
        validChoice = UserOption.GO_BACK;
      }
    }
    return validChoice;
  }

  /**
   * Option Console for Edit Boat Menu.
   */
  public UserOption editMemberBoatChoice() {
    UserOption validChoice = null;

    while (validChoice == null) {
      editBoatMenu();
      displayMessage(Messages.ENTER_CHOICE.str);
      String c = readUserInput();

      if (c.equalsIgnoreCase("1")) {
        validChoice = UserOption.EDIT_BOAT_LENGTH;
      } else if (c.equalsIgnoreCase("2")) {
        validChoice = UserOption.EDIT_BOAT_TYPE;
      } else if (c.equalsIgnoreCase("3")) {
        validChoice = UserOption.EDIT_BOAT_ALL;
      } else {
        validChoice = UserOption.GO_BACK;
      }
    }
    return validChoice;
  }

  /**
   * Main menu console.
   */
  private void mainMenuAuth() {
    System.out.print("\n1. Add a new member." + "\n2. Delete a member." + "\n3. Edit member info."
        + "\n4. List members." + "\n5. Show specific member info." + "\n6. Search Members."
        + "\n7. Add Boat." + "\n8. Delete Boat." + "\n9. Edit Boat."
        + "\n10. Log out." + "\n--> Press enter to exit.\n");
  }

  private void mainMenu() {
    System.out.print("\n1. List members." + "\n2. Show specific member info." + "\n3. Search Members."
            + "\n4. Log in." + "\n--> Press enter to exit.\n");
  }

  /**
   * Member edit parameter menu.
   */
  private void editMemberMenu() {
    System.out.print("\nSelect info to edit:" + "\n1. Edit name." + "\n2. Edit personal number." + "\n3. Edit all."
        + "\n--> Press enter to return to main menu.");
  }

  /**
   * Menu for member information display method.
   */
  private void listMembersMenu() {
    System.out.print("\nSelect how to list members:"
        + "\n1. Verbose list: name, personal number, member id and boats with boat information."
        + "\n2. Compact list: name, member id and number of boats." + "\n--> Press enter to return to main menu.");
  }

  /**
   * Menu for member search information display.
   */
  private void searchMembersMenu() {
    System.out.print("\nSelect how to search members:"
        + "\n1. Members with certain name."
        + "\n2. Members who own boats of the certain type." + "\n--> Press enter to return to main menu.");
  }

  /**
   * Boat edit parameter selection.
   */
  private void editBoatMenu() {
    System.out.print("\nSelect info to edit:" + "\n1. Edit Length." + "\n2. Edit Type." + "\n3. Edit all."
        + "\n--> Press enter to return to main menu.");
  }

  /**
   * Boat type selection menu.
   */
  @Override
  public void boatTypeMenu() {
    System.out.print("\nSelect boat type:");
    int i = 1;
    for (Boat.BoatType value : Boat.BoatType.values()) {
      System.out.format("%n%d. %s.", i, value.str);
      i++;
    }
  }

  /**
   * Pretty print separator.
   */
  private void separatorDashedLine() {
    System.out.println("------------------------------------------------------------");
  }

  /**
   * Pretty print of the member headers for the verbose list view.
   */
  public void headerMemberVerboseList() {
    System.out.printf("%n%16s %16s %16s%n", "Member Name", "Personal Number", "MemberID");
    separatorDashedLine();
  }

  /**
   * Pretty print of the boat headers for the verbose list view.
   */
  private void headerBoatVerboseList() {
    System.out.println("\nBoats: ");
    System.out.printf("%n%16s %16s %16s%n", "Reg Number", "Length", "Type");
  }

  /**
   * Pretty print of the headers for the compact list view.
   */
  public void headerMemberCompactList() {
    System.out.printf("%n%16s %16s %16s%n", "Member Name", "MemberID", "Num of Boats");
    separatorDashedLine();
  }

  /**
   * General read method.
   */
  @Override
  public String readUserInput() {
    try {
      String input = bufferedReader.readLine();
      return input.trim();
    } catch (IOException | NullPointerException ex) {
      displayMessage("\nInvalid Input.");
      return null;
    }
    
  }

  /**
   * General display method.
   */
  @Override
  public void displayMessage(String message) {
    System.out.print(message);
  }

  /**
   * Displays members compact information.
   */
  @Override
  public void displayCompactMembersInfo(Iterator<Map.Entry<String, Member>> iterator) {
    if (iterator == null) {
      displayMessage(Messages.NOT_FOUND_MEMBERS.str);
    } else {
      headerMemberCompactList();
      while (iterator.hasNext()) {
        Map.Entry<String, Member> e = iterator.next();
        System.out.printf("%16s %16s %10d boat%s%n", e.getValue().getName(), e.getKey(), e.getValue().numberOfBoats(),
            e.getValue().numberOfBoats() == 1 ? "" : "s");
      }
    }
  }

  /**
   * Displays members verbose information.
   */
  @Override
  public void displayVerboseMembersInfo(Iterator<Map.Entry<String, Member>> iterator) {
    if (iterator == null) {
      displayMessage(Messages.NOT_FOUND_MEMBERS.str);
    } else {
      headerMemberVerboseList();
      while (iterator.hasNext()) {
        Map.Entry<String, Member> e = iterator.next();
        displayCurrentMemberInfo(e);
        separatorDashedLine();
      }
    }
  }

  /**
   * Displays specific member verbose information.
   */
  @Override
  public void displaySpecificMemberInfo(Iterator<Map.Entry<String, Member>> iterator, String memberId) {
    headerMemberVerboseList();
    while (iterator.hasNext()) {
      Map.Entry<String, Member> e = iterator.next();
      if (e.getKey().equals(memberId)) {
        displayCurrentMemberInfo(e);
      }
    }
  }

  /**
   * Displays boat information.
   */
  private void displayBoatInfo(Iterator<Map.Entry<String, Boat>> iterator) {
    headerBoatVerboseList();
    while (iterator.hasNext()) {
      Map.Entry<String, Boat> e = iterator.next();
      System.out.printf("%16s %16.2f %16s%n", e.getKey(), e.getValue().getLength(), e.getValue().getType());
    }
  }

  private void displayCurrentMemberInfo(Map.Entry<String, Member> e) {
    System.out.printf("%16s %16s %16s%n", e.getValue().getName(), e.getValue().getPnr(), e.getKey());
    if (e.getValue().numberOfBoats() > 0) {
      displayBoatInfo(e.getValue().boatsIterator());
    }
  }

}
