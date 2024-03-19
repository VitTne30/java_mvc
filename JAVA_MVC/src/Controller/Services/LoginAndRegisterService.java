package Controller.Services;

import Controller.DbConnection.DataConnection;
import Model.ModelUser;
import View.ForgetView;
import View.LoginAndRegisterView;
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
public class LoginAndRegisterService {

    private DataConnection databaseConnection;
    private LoginAndRegisterView loginView;
    private final Connection con;
    private boolean isLogin;

    public LoginAndRegisterService(LoginAndRegisterView newLoginView) throws SQLException {
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
        loginView.getBtnRegis().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regisComponent();
            }
        });
        //
        loginView.getBtnToRegis().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isLogin) {
                    moveLogin();
                }
                isLogin = !isLogin;
            }
        });
        //
        loginView.getBtnToLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isLogin) {
                    moveRegis();
                }
                isLogin = !isLogin;
            }
        });
        //
        loginView.getBtnForget().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    forgetPassComponent();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginAndRegisterService.class.getName()).log(Level.SEVERE, null, ex);
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

                new MainView(user).setVisible(true);
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
            Logger.getLogger(LoginAndRegisterService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //
    private void regisComponent() {
        String fullName = loginView.getFullName().getText().trim();
        String name = loginView.getUserNameRegis().getText().trim();
        String id;
        String pass = String.valueOf(loginView.getPasswordRegis().getPassword());
        String role = "Staff";
        String phone = loginView.getPhone().getText();
        boolean duplicate = false;
        String sql = "SELECT * FROM tbl_taikhoan WHERE tentaikhoan=?";
        PreparedStatement p0;
        try {
            p0 = con.prepareStatement(sql);
            p0.setString(1, name);
            ResultSet r1 = p0.executeQuery();
            if (r1.next()) {
                duplicate = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginAndRegisterService.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!name.isEmpty() || !pass.isEmpty() || !fullName.isEmpty() || !phone.isEmpty()) {
            if (duplicate) {
                JOptionPane.showMessageDialog(loginView, "Tài khoản đã tồn tại!",
                        "Thông báo", JOptionPane.ERROR_MESSAGE);
                loginView.getUserNameRegis().requestFocus();
            } else {
                try {
                    ModelUser user;
                    PreparedStatement p1 = con.prepareStatement("SELECT MAX(id) as id FROM tbl_taikhoan");
                    ResultSet r = p1.executeQuery();
                    r.next();
                    int userID = r.getInt("id") + 1;

                    //Insert account
                    String sql_ND = "INSERT INTO tbl_taikhoan (id,tentaikhoan, matkhau,vaitro) VALUES (?,?,?,'Staff')";
                    PreparedStatement p = con.prepareStatement(sql_ND);
                    p.setInt(1, userID);
                    p.setString(2, loginView.getUserNameRegis().getText().trim());
                    p.setString(3, String.valueOf(loginView.getPasswordRegis().getPassword()));
                    p.execute();
                    user = new ModelUser(userID, loginView.getUserNameRegis().getText().trim(),
                            String.valueOf(loginView.getPasswordRegis().getPassword()), "Staff");
                    //InsertStaff
                    String sql_ND2 = "INSERT INTO tbl_nhanvien (id,hoten, sdt) VALUES (?,?,?)";
                    PreparedStatement p2 = con.prepareStatement(sql_ND2);
                    p2.setInt(1, userID);
                    p2.setString(2, loginView.getFullName().getText().trim());
                    p2.setString(3, loginView.getPhone().getText().trim());
                    p2.execute();
                    JOptionPane.showMessageDialog(loginView, "Tạo tài khoản thành công!",
                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    new MainView(user).setVisible(true);
                    loginView.dispose();
                    r.close();
                    p.close();
                    p1.close();
                    p2.close();
                    databaseConnection.releaseConnection(con);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginAndRegisterService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(loginView, "Vui lòng điền đủ thông tin!",
                    "Thông báo", JOptionPane.ERROR_MESSAGE);
        }

    }

    //
    private void moveLogin() {
        Thread moveThread2;
        moveThread2 = new Thread(() -> {
            int speed = 10;
            int x = loginView.getLogin().getY();
            int y = loginView.getRegister().getY();
            while (x > -500 && y > 0) {
                try {
                    Thread.sleep(10); // 
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                x -= speed;
                y -= speed;
                loginView.getLogin().setBounds(0, x, 800, 500);
                loginView.getRegister().setBounds(0, y, 800, 500);

            }
        });
        moveThread2.start();

    }

    //
    private void moveRegis() {
        Thread moveThread;
        moveThread = new Thread(() -> {
            int speed = 10;
            int y = loginView.getRegister().getY();
            int x = loginView.getLogin().getY();
            while (y < 500 && x < 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                y += speed;
                x += speed;
                loginView.getRegister().setLocation(0, y);
                loginView.getLogin().setLocation(0, x);
            }

        });
        moveThread.start();
    }

    //
    private void forgetPassComponent() throws SQLException {
        ForgetView newFview = new ForgetView();
        ForgetService newFService = new ForgetService(newFview);
        newFview.setVisible(true);
        
    }

}
