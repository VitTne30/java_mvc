package Controller;

import DbConnection.DataConnection;
import Model.ModelUser;
import View.ForgetView;
import View.LoginView;
import View.MainView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class LoginController {

    private DataConnection databaseConnection;
    private LoginView loginView;
    private final Connection con;
    private char pas;
    private Icon show,hide;

    public LoginController(LoginView newLoginView) throws SQLException {
        databaseConnection = DataConnection.getInstance();
        this.loginView = newLoginView;

        con = (Connection) databaseConnection.getConnection();

        //
        pas = loginView.getPassField().getEchoChar();
        hide = new ImageIcon(getClass().getResource("/Icon/hide.png"));
        show = new ImageIcon(getClass().getResource("/Icon/show.png"));
        loginView.getPassField().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (loginView.getPassField().getSuffixIcon().equals(hide)) {
                    loginView.getPassField().setSuffixIcon(show);
                    loginView.getPassField().setEchoChar((char) 0);
                } else {
                    loginView.getPassField().setSuffixIcon(hide);
                    loginView.getPassField().setEchoChar(pas);
                }
            }
        });
        //
        loginView.getBtnForget().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginView.getBtnForget().setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginView.getBtnForget().setForeground(new Color(245, 245, 245));
            }
        });
        //LoginComponent
        loginView.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginComponent();
            }
        });
        ////
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
