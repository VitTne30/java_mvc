package java_mvc;

import Controller.DbConnection.DataConnection;
import Controller.Serviece.LoginAndRegisterService;
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
    private static DataConnection con;

    public static void main(String[] args) throws SQLException {
        LoginAndRegisterView lgView = new LoginAndRegisterView();
        LoginAndRegisterService loginManager = new LoginAndRegisterService(lgView);
        lgView.setVisible(true);
    }
    
}
