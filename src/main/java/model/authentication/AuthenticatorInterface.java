package model.authentication;

import model.domain.Secretary;


/**
 * Authenticator interface.
 */
public interface AuthenticatorInterface {
  boolean login(Secretary secretary, String username, String password);
  
  boolean getAuthStatus();
}

