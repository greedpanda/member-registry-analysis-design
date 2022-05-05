package model.authentication;

import model.domain.Secretary;

/**
 * Authenticator for login purposes.
 */
public class Authenticator implements AuthenticatorInterface {

  private boolean status;

  /**
   * Login function.
   */
  public boolean login(Secretary secretary, String username, String password) {
    if (secretary.getId().equals(username) && secretary.getPassword().equals(password)) {
      this.status = true;
      return true;
    }
    this.status = false;
    return false;
  }

  @Override
  public boolean getAuthStatus() {
    return this.status;
  }
}
