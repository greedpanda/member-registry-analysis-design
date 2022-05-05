package controller;

import java.util.Iterator;
import java.util.Map;
import model.domain.Member;
import model.domain.Register;
import model.search.SearchBoatByRegnum;
import model.search.SearchMemberById;
import view.Messages;
import view.UserInterface;

/**
 * Delete Boat Action.
 */
public class DeleteBoatAction implements Action {

  @Override
  public void executeAction(Register register, UserInterface view) {
    String memberId = InputValidation.MEMBERID.validate(view, Messages.MEMBERID.str);
    Iterator<Map.Entry<String, Member>> map = register.searchMember(new SearchMemberById(memberId));
    if (map != null) {
      String regnum = InputValidation.REGNUM.validate(view, Messages.REGNUM.str);
      if (register.searchMember(new SearchBoatByRegnum(regnum), map) != null) {
        register.delMemberBoat(memberId, regnum);
        view.displayMessage(Messages.SUCCESS.str);
      } else {
        view.displayMessage(Messages.BOAT_DOES_NOT_EXISTS.str);
      }
    } else {
      view.displayMessage(Messages.MEMBER_DOES_NOT_EXISTS.str);
    }
  }

}
