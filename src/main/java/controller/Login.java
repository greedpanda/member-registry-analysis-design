package controller;

import model.authentication.AuthenticatorInterface;
import model.domain.Secretary;
import view.Messages;
import view.UserInterface;

/**
 * Login class.
 */

public class Login {

  /**
   * Execute login action.
   */
  public boolean executeLogin(Secretary secretary, UserInterface view, AuthenticatorInterface authenticator) {
    String username = InputValidation.LOGINID.validate(view, Messages.LOGINID.str);
    String password = InputValidation.PASSWORD.validate(view, Messages.PASSWORD.str);
    if (authenticator.login(secretary, username, password)) {
      return true;
    } else {
      view.displayMessage(Messages.LOGININVALID.str);
      return false;
    }

  }
}
