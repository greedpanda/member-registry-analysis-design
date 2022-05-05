package controller;

import model.domain.Boat.BoatType;
import model.domain.Register;
import model.search.SearchBoatByType;
import model.search.SearchMemberByName;
import view.Messages;
import view.UserInterface;
import view.UserOption;

/**
 * Search Member Action.
 */
public class SearchMemberAction implements Action {

  /**
   * Execute Search Menu depending on user choice.
   *
   */
  @Override
  public void executeAction(Register register, UserInterface view) {
    UserOption choice = view.getUserOption(UserOption.MENU_SEARCH);
    String searchRequest;

    switch (choice) {
      case SEARCH_ON_NAME:
        searchRequest = InputValidation.NAME_SEARCH.validate(view, Messages.NAME_SUBSTRING.str);
        view.displayVerboseMembersInfo(register.searchMember(new SearchMemberByName(searchRequest)));
        break;
      case SEARCH_ON_BOATTYPE:
        view.boatTypeMenu();
        searchRequest = InputValidation.TYPE_MENU_CHOICE.validate(view, Messages.ENTER_CHOICE.str);
        view.displayVerboseMembersInfo(
            register.searchMember(
                new SearchBoatByType(BoatType.convertToValue(Integer.parseInt(searchRequest) - 1))));
        break;
      case GO_BACK:
      default:
        break;

    }
  }

}
