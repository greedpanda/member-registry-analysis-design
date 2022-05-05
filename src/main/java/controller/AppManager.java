package controller;

import model.authentication.AuthenticatorInterface;
import model.domain.Register;
import model.domain.Secretary;
import model.persistence.PersistenceInterface;
import view.Messages;
import view.UserInterface;
import view.UserOption;

/**
 * Main Controller.
 */
public class AppManager {

  /**
   * App Manager constructor.
   */
  public AppManager() {

  }

  /**
   * Main method to run the app.
   */
  public void runApp(UserInterface view, Register register, PersistenceInterface dataLoader,
                     AuthenticatorInterface auth) {

    Action currentAction = null;
    Secretary secretary = new Secretary();  // hardcoded secretary

    if (register.setMembers(dataLoader.loadData())) {
      boolean quit = false;
      boolean authenticated = false;
      while (!quit) {

        // Register "knows" the authentication status on every loop
        register.setIsAuthenticated(auth);
        
        UserOption choice;
        if (authenticated) {
          choice = view.getUserOption(UserOption.MENU_MAIN_AUTH);
        } else {
          choice = view.getUserOption(UserOption.MENU_MAIN);
        }
        switch (choice) {

          case ADD_MEMBER: // create member
            currentAction = new AddMemberAction();
            break;

          case DELETE_MEMBER: // delete member
            currentAction = new DeleteMemberAction();
            break;

          case EDIT_MEMBER: // edit member info
            currentAction = new EditMemberAction();
            break;

          case LIST_MEMBER: // list members
          case SHOW_MEMBER_INFO: // specific member info
            ListMenu currentListMenu = new ListMenu();
            currentListMenu.executeListMenu(register, view, choice);
            break;

          case SEARCH_MEMBERS:
            currentAction = new SearchMemberAction();
            break;

          case ADD_BOAT: // add boat
            currentAction = new AddBoatAction();
            break;

          case DELETE_BOAT: // delete boat
            currentAction = new DeleteBoatAction();
            break;

          case EDIT_BOAT:
            currentAction = new EditBoatAction();
            break;

          case LOG_IN:
            Login login = new Login();
            authenticated = login.executeLogin(secretary, view, auth);
            break;
          case LOG_OUT:
            authenticated = false;
            break;

          case EXIT:
          default:
            quit = true;
            if (dataLoader.saveData(register.membersIterator())) {
              view.displayMessage(Messages.EXIT.str);
            } else {
              view.displayMessage(Messages.ERRLOADING.str);
            }
            break;
        }

        if (!choice.equals(UserOption.LOG_OUT) && !choice.equals(UserOption.LOG_IN) && !choice.equals(UserOption.EXIT)
                && !choice.equals(UserOption.LIST_MEMBER) && !choice.equals(UserOption.SHOW_MEMBER_INFO)) {
          try {
            currentAction.executeAction(register, view);
          } catch (NullPointerException e) {
            view.displayMessage(Messages.ERRLOADING.str);
          }
        }
      }
    } else {
      view.displayMessage(Messages.ERRLOADING.str);
    }
  }
}
