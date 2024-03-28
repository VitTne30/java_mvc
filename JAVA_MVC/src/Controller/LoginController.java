package Controller;

import DbConnection.DataConnection;
import Model.ModelUser;
import View.ForgetView;
import View.LoginView;
import View.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class LoginController {

    private DataConnection databaseConnection;
    private LoginView loginView;
    private final Connection con;
    private boolean isLogin;

    public LoginController(LoginView newLoginView) throws SQLException {
        databaseConnection = DataConnection.getInstance();
        this.loginView = newLoginView;

        con = (Connection) databaseConnection.getConnection();
        isLogin = true;

        //LoginComponent
        loginView.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginComponent();
            }
        });
        //RegisterComponent
//        loginView.getBtnRegis().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                regisComponent();
//            }
//        });
        //
        
        //
        loginView.getBtnForget().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    forgetPassComponent();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void loginComponent() {
        try {
            ModelUser user = null;
            String sql = "SELECT * FROM tbl_taikhoan WHERE tentaikhoan=? AND matkhau=?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setString(1, loginView.getUserField().getText());
            p.setString(2, String.valueOf(loginView.getPassField().getPassword()));
            ResultSet r = p.executeQuery();
            if (r.next()) {
                int UserID = r.getInt("id");
                String name = r.getString("tentaikhoan");
                String password = r.getString("matkhau");
                String role = r.getString("vaitro");
                user = new ModelUser(UserID, name, password, role);

                MainView mainView = new MainView(user);
                MainController mainSer = new MainController(mainView);
                mainView.setVisible(true);
                loginView.dispose();

            } else {
                JOptionPane.showMessageDialog(loginView, "Đăng nhập thất bại!\n"
                        + "Vui lòng kiểm tra lại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                loginView.getUserField().requestFocus();
            }
            databaseConnection.releaseConnection(con);
            r.close();
            p.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //
    private void forgetPassComponent() throws SQLException {
        ForgetView newFview = new ForgetView();
        ForgetController newFService = new ForgetController(newFview);
        newFview.setVisible(true);
        
    }

}
