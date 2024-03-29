package Controller;

import DbConnection.DataConnection;
import Model.ModelUser;
import View.AccountView;
import View.ChangePassView;
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
public class ChangePassControlller {

    private DataConnection databaseConnection;
    private Connection con;
    private ChangePassView passView;
    private boolean found = false;
    private char pas,pas2,pas3;
    private Icon show, hide;
    private ModelUser u, inUseUser;
    private AccountView inUseAcc;

    public ChangePassControlller(ChangePassView newView, AccountView newAcc) throws SQLException {
        databaseConnection = DataConnection.getInstance();
        con = (Connection) databaseConnection.getConnection();
        this.passView = newView;
        this.inUseAcc = newAcc;
        inUseUser = passView.getInUseUser();
        ////////
        hide = new ImageIcon(getClass().getResource("/Icon/hide.png"));
        show = new ImageIcon(getClass().getResource("/Icon/show.png"));
        pas = passView.getTxtOld().getEchoChar();
        passView.getTxtOld().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (passView.getTxtOld().getSuffixIcon().equals(hide)) {
                    passView.getTxtOld().setSuffixIcon(show);
                    passView.getTxtOld().setEchoChar((char) 0);
                } else {
                    passView.getTxtOld().setSuffixIcon(hide);
                    passView.getTxtOld().setEchoChar(pas);
                }
            }
        });
        pas2 = passView.getTxtNew().getEchoChar();
        passView.getTxtNew().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (passView.getTxtNew().getSuffixIcon().equals(hide)) {
                    passView.getTxtNew().setSuffixIcon(show);
                    passView.getTxtNew().setEchoChar((char) 0);
                } else {
                    passView.getTxtNew().setSuffixIcon(hide);
                    passView.getTxtNew().setEchoChar(pas);
                }
            }
        });
        pas3 = passView.getTxtConfirm().getEchoChar();
        passView.getTxtConfirm().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (passView.getTxtConfirm().getSuffixIcon().equals(hide)) {
                    passView.getTxtConfirm().setSuffixIcon(show);
                    passView.getTxtConfirm().setEchoChar((char) 0);
                } else {
                    passView.getTxtConfirm().setSuffixIcon(hide);
                    passView.getTxtConfirm().setEchoChar(pas);
                }
            }
        });
        /////////////////
        //Button Confirm
        passView.getBtnConfirm().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPass = String.valueOf(passView.getTxtNew().getPassword());
                String rePass = String.valueOf(passView.getTxtConfirm().getPassword());

                if (checkPassword()) {
                    if (newPass != null && newPass.equals(rePass)) {
                        try {
                            String sql_ND = "UPDATE tbl_taikhoan SET matkhau=? WHERE id=?";
                            PreparedStatement p1 = con.prepareStatement(sql_ND);
                            p1.setString(1, rePass);
                            p1.setInt(2, u.getUserId());
                            p1.execute();
                            JOptionPane.showMessageDialog(passView, "Đổi mật khẩu thành công!",
                                    "Thông báo", JOptionPane.OK_OPTION);
                            //
                            inUseUser.setPassword(rePass);
                            AccountView newAccView = new AccountView(inUseUser);
                            AccountController newAccSer = new AccountController(newAccView);
                            inUseAcc.removeAll();
                            inUseAcc.add(newAccView);
                            inUseAcc.revalidate();
                            inUseAcc.repaint();
                            p1.close();
                            databaseConnection.releaseConnection(con);
                        } catch (SQLException ex) {
                            Logger.getLogger(ForgetController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(passView, "Xác nhận mật khẩu không đúng!",
                                "Thông báo", JOptionPane.OK_OPTION);
                    }
                }
            }
        });
        //Button Cancel
        passView.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountView newAccView = new AccountView(inUseUser);
                try {
                    AccountController newAccSer = new AccountController(newAccView);
                } catch (SQLException ex) {
                    Logger.getLogger(ChangePassControlller.class.getName()).log(Level.SEVERE, null, ex);
                }
                inUseAcc.removeAll();
                inUseAcc.add(newAccView);
                inUseAcc.revalidate();
                inUseAcc.repaint();
                databaseConnection.releaseConnection(con);
            }
        });
    }

    private boolean checkPassword() {
        char[] passwordChars = passView.getTxtOld().getPassword();
        String password = new String(passwordChars);
        try {
            String sql = "SELECT * FROM tbl_taikhoan WHERE id = ? AND matkhau = ?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setString(1, String.valueOf(inUseUser.getUserId()));
            p.setString(2, password);
            ResultSet r = p.executeQuery();
            if (r.next()) {
                found = true;
                u = new ModelUser(r.getInt("id"));
                passView.getLbMessage().setText("Mật khẩu chính xác!");
                passView.getLbMessage().setForeground(Color.GREEN);
            } else {
                found = false;
                passView.getLbMessage().setText("Mật khẩu không đúng!");
                passView.getLbMessage().setForeground(Color.RED);
            }
            p.close();
            r.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChangePassControlller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return found;
    }

}
