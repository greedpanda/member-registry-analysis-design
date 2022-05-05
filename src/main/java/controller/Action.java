package controller;

import model.domain.Register;
import view.UserInterface;

/**
 * Action interface for Add Member, AddBoat,
 * Delete Member, Edit Member, Delete Boat, Edit Boat.
 */
public interface Action {

  void executeAction(Register register, UserInterface view);

}
