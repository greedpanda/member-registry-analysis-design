package controller;

import model.domain.Register;
import model.search.SearchMemberById;
import view.Messages;
import view.UserInterface;

/**
 * Delete member Action.
 */
public class DeleteMemberAction implements Action {

  @Override
  public void executeAction(Register register, UserInterface view) {
    String memberId = InputValidation.MEMBERID.validate(view, Messages.MEMBERID.str);
    if (register.searchMember(new SearchMemberById(memberId)) != null) {
      if (register.delMember(memberId)) {
        view.displayMessage(Messages.SUCCESS.str);
      } else {
        view.displayMessage(Messages.LOGININVALID.str);
      }
    } else {
      view.displayMessage(Messages.MEMBER_DOES_NOT_EXISTS.str);
    }
  }
}
