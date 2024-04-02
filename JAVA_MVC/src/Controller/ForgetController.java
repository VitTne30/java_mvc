package Controller;

import DbConnection.DataConnection;
import Model.ModelUser;
import View.ForgetView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class ForgetController {

    private DataConnection databaseConnection;
    private Connection con;
    private ForgetView forgetView;
    private boolean found = false;
    private ModelUser u;
    private char pas,pas2;
    private Icon hide,show;

    public ForgetController(ForgetView newForgetView) throws SQLException {
        databaseConnection = DataConnection.getInstance();
        con = (Connection) databaseConnection.getConnection();

        this.forgetView = newForgetView;
        //
        hide = new ImageIcon(getClass().getResource("/Icon/hide.png"));
        show = new ImageIcon(getClass().getResource("/Icon/show.png"));
        pas = forgetView.getNewPass().getEchoChar();
        forgetView.getNewPass().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (forgetView.getNewPass().getSuffixIcon().equals(hide)) {
                    forgetView.getNewPass().setSuffixIcon(show);
                    forgetView.getNewPass().setEchoChar((char) 0);
                } else {
                    forgetView.getNewPass().setSuffixIcon(hide);
                    forgetView.getNewPass().setEchoChar(pas);
                }
            }
        });
        //
        pas2 = forgetView.getRePass().getEchoChar();
        forgetView.getRePass().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (forgetView.getRePass().getSuffixIcon().equals(hide)) {
                    forgetView.getRePass().setSuffixIcon(show);
                    forgetView.getRePass().setEchoChar((char) 0);
                } else {
                    forgetView.getRePass().setSuffixIcon(hide);
                    forgetView.getRePass().setEchoChar(pas2);
                }
            }
        });
        //
        forgetView.getBtnBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forgetView.dispose();
                databaseConnection.releaseConnection(con);
            }
        });

        forgetView.getBtnConfirm().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPass = String.valueOf(forgetView.getNewPass().getPassword());
                String rePass = String.valueOf(forgetView.getRePass().getPassword());
                if (checkMail()) {
                    if (newPass != null && newPass.equals(rePass)) {
                        try {
                            String sql_ND = "UPDATE tbl_taikhoan SET matkhau=? WHERE id=?";
                            PreparedStatement p1 = con.prepareStatement(sql_ND);
                            p1.setString(1, String.valueOf(forgetView.getRePass().getPassword()));
                            p1.setInt(2, u.getUserId());
                            p1.execute();
                            JOptionPane.showMessageDialog(forgetView, "Đổi mật khẩu thành công!",
                                    "Thông báo", JOptionPane.OK_OPTION);
                            forgetView.dispose();
                            p1.close();
                            databaseConnection.releaseConnection(con);
                        } catch (SQLException ex) {
                            Logger.getLogger(ForgetController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(forgetView, "Xác nhận mật khẩu chưa chính xác!",
                                "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
    }

    private boolean checkMail() {
        try {
            String sql = "SELECT * FROM tbl_taikhoan WHERE email=?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setString(1, forgetView.getFindAcc().getText());
            ResultSet r = p.executeQuery();
            if (r.next()) {
                u = new ModelUser(r.getInt("id"));
                found = true;
                forgetView.getErrorLb().setText("Tìm thấy tài khoản!");
                forgetView.getErrorLb().setForeground(Color.GREEN);
            } else {
                found = false;
                forgetView.getErrorLb().setText("Tài khoản không tồn tại!");
                forgetView.getErrorLb().setForeground(Color.red);
            }
            p.close();
            r.close();
        } catch (SQLException ex) {
            Logger.getLogger(ForgetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return found;
    }
}
