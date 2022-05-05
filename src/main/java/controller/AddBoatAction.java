package controller;

import model.domain.Boat.BoatType;
import model.domain.Register;
import model.search.SearchBoatByRegnum;
import model.search.SearchMemberById;
import view.Messages;
import view.UserInterface;

/**
 * Add Boat Action.
 */
public class AddBoatAction implements Action {

  @Override
  public void executeAction(Register register, UserInterface view) {
    String memberId = InputValidation.MEMBERID.validate(view, Messages.MEMBERID.str);
    if (register.searchMember(new SearchMemberById(memberId)) != null) {
      String regnum = InputValidation.REGNUM.validate(view, Messages.REGNUM.str);
      if (register.searchMember(new SearchBoatByRegnum(regnum)) == null) {
        double length = Double.parseDouble(InputValidation.LENGTH.validate(view, Messages.LENGTH.str));
        view.boatTypeMenu();
        String type = InputValidation.TYPE_MENU_CHOICE.validate(view, Messages.ENTER_CHOICE.str);
        register.addMemberBoat(memberId, regnum, length, BoatType.convertToValue(Integer.parseInt(type) - 1));
        view.displayMessage(Messages.SUCCESS.str);

      } else {
        view.displayMessage(Messages.BOAT_EXISTS.str);
      }

    } else {
      view.displayMessage(Messages.MEMBER_DOES_NOT_EXISTS.str);
    }
  }

}
