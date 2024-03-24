package Controller.Services;

import Controller.DbConnection.DataConnection;
import Model.ModelCustomer;
import Model.ModelStaff;
import Model.ModelUser;
import View.AccountView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class AccountService {
    private DataConnection databaseConnection;
    private Connection con;
    private AccountView accView;
    private ModelUser user;
    private ModelStaff staff;
    public AccountService(AccountView newView) throws SQLException{
        databaseConnection = DataConnection.getInstance();
        con = (Connection) databaseConnection.getConnection();
        //
        this.accView =newView;
        this.user = accView.getUser();
        accView.getIdLb().setText(String.valueOf(user.getUserId()));
        accView.getUsNameLb().setText(user.getUserName());
        accView.getPass().setText(user.getPassword());
        accView.getRole().setText(user.getRole());
        //
        staff = getData(user.getUserId());
        accView.getNameLb().setText(staff.getName());
        accView.getAddreddLb().setText(staff.getAddress());
        accView.getGenderLb().setText(staff.getDate());
        accView.getPhoneLb().setText(staff.getPhone());
        accView.getEmailLb().setText(staff.getEmail());
        //
        accView.getBtnChangepass().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("1111");
            }
        });
    }
    private ModelStaff getData(int id) throws SQLException{
        ModelStaff data =null;
        String sql = "SELECT hoten,DATE_FORMAT(ngaysinh, '%d-%m-%Y') AS ngaysinh,"
                + "diachi, sdt, email FROM tbl_nhanvien WHERE id = ?;";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            String name = r.getString(1);
            String date = r.getString(2);
            String address = r.getString(3);
            String phone = r.getString(4);
            String email = r.getString(5);
            data = new ModelStaff(id, name, date, address, phone, email);
        }
        p.close();
        r.close();
        databaseConnection.releaseConnection(con);
        return data;
    }
    
    
}
