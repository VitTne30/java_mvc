package Controller;

import DbConnection.DataConnection;
import Model.ModelUser;
import View.ForgetView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public ForgetController(ForgetView newForgetView) throws SQLException {
        databaseConnection = DataConnection.getInstance();
        con = (Connection) databaseConnection.getConnection();

        this.forgetView = newForgetView;

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
                if (checkName()) {
                    if (newPass!= null && newPass.equals(rePass)) {
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
                        JOptionPane.showMessageDialog(forgetView, "Xác nhận mật khẩu không đúng!",
                                "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
    }

    private boolean checkName() {
        try {
            String sql = "SELECT * FROM tbl_taikhoan WHERE tentaikhoan=?";
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
