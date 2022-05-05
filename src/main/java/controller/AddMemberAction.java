package controller;

import model.domain.Register;
import model.search.SearchMemberByPnr;
import view.Messages;
import view.UserInterface;

/**
 * Add Member Action.
 */
class AddMemberAction implements Action {

  @Override
  public void executeAction(Register register, UserInterface view) {
    String pnr = InputValidation.PNR.validate(view, Messages.PNR.str);
    if (register.searchMember(new SearchMemberByPnr(pnr)) == null) {
      String name = InputValidation.FULL_NAME.validate(view, Messages.NAME.str);
      if (register.addMember(name, pnr)) {
        view.displayMessage(Messages.SUCCESS.str);
      } else {
        view.displayMessage(Messages.LOGININVALID.str);
      }
    } else {
      view.displayMessage(Messages.MEMBER_EXISTS.str);
    }
  }

}
