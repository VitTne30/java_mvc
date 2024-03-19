package java_mvc;

import Controller.Services.LoginAndRegisterService;
import View.LoginAndRegisterView;
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
        LoginAndRegisterView lgView = new LoginAndRegisterView();
        LoginAndRegisterService loginManager = new LoginAndRegisterService(lgView);
        lgView.setVisible(true);
    }
    
}
