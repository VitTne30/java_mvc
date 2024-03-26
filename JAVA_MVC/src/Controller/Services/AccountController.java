package Controller.Services;

import Controller.DbConnection.DataConnection;

import Model.ModelUser;
import View.AccountView;
import View.ChangePassView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class AccountController {

    private DataConnection databaseConnection;
    private Connection con;
    private AccountView accView;
    private ModelUser user,staff;
    
    public AccountController(AccountView newView) throws SQLException {
        databaseConnection = DataConnection.getInstance();
        con = (Connection) databaseConnection.getConnection();
        //
        this.accView = newView;
        this.user = accView.getUser();
        accView.getIdLb().setText(String.valueOf(user.getUserId()));
        accView.getUsNameLb().setText(user.getUserName());
        accView.getPass().setText(user.getPassword());
        accView.getRole().setText(user.getRole());
        //
        staff = getData(user.getUserId());
        accView.getNameLb().setText(staff.getName());
        accView.getAddreddLb().setText(staff.getAddress());
        accView.getPhoneLb().setText(staff.getPhone());
        accView.getEmailLb().setText(staff.getEmail());
        //
        accView.getBtnChangepass().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangePassView changeView = new ChangePassView(user);
                try {
                    ChangePassControlller changeSer = new ChangePassControlller(changeView, accView);
                } catch (SQLException ex) {
                    Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
                }
                accView.removeAll();
                accView.setBackground(new Color(240,240,240));
                accView.add(changeView);
                changeView.setVisible(true);
                accView.revalidate();
                accView.repaint();
                databaseConnection.releaseConnection(con);
            }
        });
    }

    private ModelUser getData(int id) throws SQLException {
        ModelUser data = null;
        String sql = "SELECT hoten,diachi, sdt, email FROM tbl_taikhoan WHERE id = ?;";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            String name = r.getString(1);
            String address = r.getString(2);
            String phone = r.getString(3);
            String email = r.getString(4);
            data = new ModelUser(id, name, address, phone, email);
        }
        p.close();
        r.close();
        databaseConnection.releaseConnection(con);
        return data;
    }

}
