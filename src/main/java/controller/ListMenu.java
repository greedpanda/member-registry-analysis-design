package controller;

import model.domain.Register;
import model.search.SearchMemberById;
import view.Messages;
import view.UserInterface;
import view.UserOption;

/**
 * List menus to select between verbose list,
 * compact list or show specific member information.
 */
class ListMenu {

  /**
   * Execute List Menu depending on user choice.
   */
  void executeListMenu(Register register, UserInterface view, UserOption choice) {

    if (choice.equals(UserOption.LIST_MEMBER)) {
      choice = view.getUserOption(UserOption.MENU_LIST);

      switch (choice) {
        case LIST_VERBOSE:
          view.displayVerboseMembersInfo(register.membersIterator());
          break;
        case LIST_COMPACT:
          view.displayCompactMembersInfo(register.membersIterator());;
          break;
        case GO_BACK:
        default:
          break;
      }
    } else {
      String memberId = InputValidation.MEMBERID.validate(view, Messages.MEMBERID.str);

      if (register.searchMember(new SearchMemberById(memberId)) != null) {
        view.displaySpecificMemberInfo(register.membersIterator(), memberId);
      } else {
        view.displayMessage(Messages.MEMBER_DOES_NOT_EXISTS.str);
      }
    }
  }
}
