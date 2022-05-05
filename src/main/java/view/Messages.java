package view;

/**
 * Enumeration for menu messages.
 */
public enum Messages {

  BOAT_DOES_NOT_EXISTS("\nBoat does not exist.\n"),
  BOAT_EXISTS("\nBoat already registered.\n"),
  EDIT_LENGTH("\nEnter new boat Length: "),
  EDIT_NAME("Enter new full name: "),
  EDIT_PNR("Enter new personal number: "),
  ENTER_CHOICE("\nEnter your choice: "),
  ERRLOADING("\nData loading error. Exiting ...\n"),
  EXIT("\nData is saved. Exiting ...\n"),
  LENGTH("Enter boat Length: "),
  MEMBERID("Member ID: "),
  NAME("Enter full name: "),
  PNR("Enter personal number: "),
  PASSWORD("Enter your password: "),
  REGNUM("Enter boat Registration number: "),
  SUCCESS("\nOperation succeeded!\n"),
  MEMBER_DOES_NOT_EXISTS("\nMember does not exists in the club register.\n"),
  MEMBER_EXISTS("\nMember already exists in the club register.\n"),
  NAME_SUBSTRING("Enter full name or substring: "),
  NOT_FOUND_MEMBERS("\nNo members were found in the club register.\n"),
  LOGINID("Login ID: "),
  LOGININVALID("Provided credentials were invalid ");

  public final String str;

  Messages(String str) {
    this.str = str;
  }
}
