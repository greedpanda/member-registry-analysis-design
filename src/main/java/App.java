import controller.AppManager;
import model.authentication.Authenticator;
import model.authentication.AuthenticatorInterface;
import model.domain.Register;
import model.persistence.PersistenceInterface;
import model.persistence.PersistenceManager;
import view.ConsoleUi;
import view.UserInterface;

/**
 * Responsible for staring the application.
 */
public class App {

  /**
   * Application starting point.
   */
  public static void main(String[] args) {
    AuthenticatorInterface auth = new Authenticator();
    PersistenceInterface dataLoader = new PersistenceManager("clubregister.json");
    Register register = new Register();
    UserInterface console = new ConsoleUi();
    AppManager appManager = new AppManager();

    appManager.runApp(console, register, dataLoader, auth);
  }
}
