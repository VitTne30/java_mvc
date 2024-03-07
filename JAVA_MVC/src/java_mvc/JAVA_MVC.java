package java_mvc;

import Controller.DbConnection.DataConnection;
import Controller.Serviece.LoginAndRegisterService;
import View.LoginView;

/**
 *
 * @author ADMIN
 */
public class JAVA_MVC {

    /**
     * @param args the command line arguments
     */
    private static DataConnection con;

    public static void main(String[] args) {
        LoginAndRegisterService loginManager = new LoginAndRegisterService();
        String username = "1";
        String password = "1";
        if (loginManager.authenticate(username, password)) {
            System.out.println("Login successful!");
            new LoginView().setVisible(true);
        } else {
            System.out.println("Invalid username or password.");
        }
    }
    
}
