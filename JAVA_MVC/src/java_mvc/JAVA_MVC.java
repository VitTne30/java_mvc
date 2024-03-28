package java_mvc;

import Controller.LoginController;
import View.LoginView;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class JAVA_MVC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        LoginView lgView = new LoginView();
        LoginController loginManager = new LoginController(lgView);
        lgView.setVisible(true);
    }
    
}
