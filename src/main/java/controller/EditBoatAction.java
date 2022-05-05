package controller;

import java.util.Iterator;
import java.util.Map;
import model.domain.Boat.BoatType;
import model.domain.Member;
import model.domain.Register;
import model.search.SearchBoatByRegnum;
import model.search.SearchMemberById;
import view.Messages;
import view.UserInterface;
import view.UserOption;

/**
 * Edit Boat Action.
 */
public class EditBoatAction implements Action {

  @Override
  public void executeAction(Register register, UserInterface view) {
    String memberId = InputValidation.MEMBERID.validate(view, Messages.MEMBERID.str);
    Iterator<Map.Entry<String, Member>> map = register.searchMember(new SearchMemberById(memberId));
    if (map != null) {
      String regnum = InputValidation.REGNUM.validate(view, Messages.REGNUM.str);
      if (register.searchMember(new SearchBoatByRegnum(regnum), map) != null) {
        UserOption choice = view.getUserOption(UserOption.MENU_EDIT_BOAT);

        switch (choice) {
          case EDIT_BOAT_LENGTH:
            editBoatLength(memberId, regnum, register, view);
            break;
          case EDIT_BOAT_TYPE:
            editBoatType(memberId, regnum, register, view);
            break;
          case EDIT_BOAT_ALL:
            editBoatLength(memberId, regnum, register, view);
            editBoatType(memberId, regnum, register, view);
            break;
          case GO_BACK:
          default:
            break;
        }
        if (!choice.equals(UserOption.GO_BACK)) {
          view.displayMessage(Messages.SUCCESS.str);
        }
      } else {
        view.displayMessage(Messages.BOAT_DOES_NOT_EXISTS.str);
      }
    } else {
      view.displayMessage(Messages.MEMBER_DOES_NOT_EXISTS.str);
    }
  }

  private void editBoatLength(String memberId, String regnum, Register register, UserInterface view) {
    double length = Double.parseDouble(InputValidation.LENGTH.validate(view, Messages.EDIT_LENGTH.str));
    register.editMemberBoatLength(memberId, regnum, length);

  }

  private void editBoatType(String memberId, String regnum, Register register, UserInterface view) {
    view.boatTypeMenu();
    String type = InputValidation.TYPE_MENU_CHOICE.validate(view, Messages.ENTER_CHOICE.str);
    register.editMemberBoatType(memberId, regnum, BoatType.convertToValue(Integer.parseInt(type) - 1));

  }

}