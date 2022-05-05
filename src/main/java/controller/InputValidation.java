package controller;

import java.util.regex.Pattern;
import view.UserInterface;

/**
 * Enumeration to validate input.
 */
enum InputValidation {
  FULL_NAME(Pattern.compile("^([a-zA-Z]{2,19}\\s{1}[a-zA-Z]{2,19})$"),
      "Full name must be only letters, " + "name and surname separated by a single space.\n\n"),
  LENGTH(Pattern.compile("^\\d{1,2}(?:[.]\\d{1,2})?$"), "Length must be double between 0.01 and 99.99 (use dot).\n\n"),
  MEMBERID(Pattern.compile("^(\\w{2}\\d{4})$"), "Enter a valid member ID.\n\n"),
  LOGINID(Pattern.compile("^(\\w{2}\\d{4})$"), "Enter a valid login ID.\n\n"),
  PNR(Pattern.compile("^(\\d{12})$"), "Personal number must have exactly 12 digit.\n\n"),
  PASSWORD(Pattern.compile("^(?=.*\\d).{4,8}$"), "Enter a valid password.\n\n"),
  REGNUM(Pattern.compile("^(\\d{6})$"), "Registration number must be 6 digit.\n\n"),
  TYPE_MENU_CHOICE(Pattern.compile("^([1234])$"), "Valid choice should be 1,2,3 or 4.\n\n"),
  NAME_SEARCH(Pattern.compile("^([a-zA-Z]{1,19}(\\s{1}([a-zA-Z]{1,19})?)?)$"),
      "Searched string must only be letters, possibly separated with space\n\n");

  final Pattern pattern;
  final String userWarnMsg;

  InputValidation(Pattern p, String s) {
    pattern = p;
    userWarnMsg = s;
  }

  boolean test(String s) {
    return this.pattern.asPredicate().test(s);
  }

  String validate(UserInterface view, String message) {
    String input;
    while (true) {
      view.displayMessage(message);
      input = view.readUserInput();

      if (!this.test(input)) {
        view.displayMessage(this.userWarnMsg);
      } else {
        break;
      }
    }
    return input;
  }
}