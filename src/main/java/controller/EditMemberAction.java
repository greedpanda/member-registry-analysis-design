package controller;

import model.domain.Register;
import model.search.SearchMemberById;
import model.search.SearchMemberByPnr;
import view.Messages;
import view.UserInterface;
import view.UserOption;

/**
 * Edit Member Action.
 */
public class EditMemberAction implements Action {

  private boolean success;

  /**
   * Execute Edit Member Action.
   */
  public void executeAction(Register register, UserInterface view) {
    String memberId = InputValidation.MEMBERID.validate(view, Messages.MEMBERID.str);
    if (register.searchMember(new SearchMemberById(memberId)) != null) {
      UserOption choice = view.getUserOption(UserOption.MENU_EDIT_MEMBER);
      switch (choice) {
        case EDIT_MEMBER_NAME:
          editMemberName(register, view, memberId);
          break;

        case EDIT_MEMBER_PNR:
          editMemberPnr(register, view, memberId);
          break;

        case EDIT_MEMBER_ALL:
          editMemberName(register, view, memberId);
          editMemberPnr(register, view, memberId);
          break;

        case GO_BACK:
        default:
          break;
      }
      if (!choice.equals(UserOption.GO_BACK) && success) {
        view.displayMessage(Messages.SUCCESS.str);
      }
    } else {
      view.displayMessage(Messages.MEMBER_DOES_NOT_EXISTS.str);
    }
  }

  private void editMemberName(Register register, UserInterface view, String memberId) {
    String name = InputValidation.FULL_NAME.validate(view, Messages.EDIT_NAME.str);
    register.editMemberName(memberId, name);
    success = true;
  }

  private void editMemberPnr(Register register, UserInterface view, String memberId) {
    String pnr = InputValidation.PNR.validate(view, Messages.EDIT_PNR.str);
    if (register.searchMember(new SearchMemberByPnr(pnr)) == null) {
      register.editMemberPnr(memberId, pnr);
      success = true;
    } else {
      view.displayMessage(Messages.MEMBER_EXISTS.str);
      success = false;
    }
  }
}
