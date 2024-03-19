package Controller.Services;

import Controller.DbConnection.DataConnection;
import Model.ModelCustomer;
import Swing.Table;
import View.ManageCustomerView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class ManageCustomerService {
    private DataConnection databaseConnection;
    private Connection con;
    private ManageCustomerView msView;
    private Table tableNv;
    private ArrayList<ModelCustomer> listCus = new ArrayList<>();

    public ManageCustomerService(ManageCustomerView newMS) throws SQLException {
        databaseConnection = DataConnection.getInstance();
        con = (Connection) databaseConnection.getConnection();
        this.msView = newMS;
        tableNv = msView.getTableStaff();
        //GetSTaffData
        listCus = getListNV();
        for (ModelCustomer data : listCus) {
            tableNv.addRow(new Object[]{data.getId(),data.getName(),
            data.getPhone(),data.getEmail()});
        }
        ////
        msView.getNumberLb().setText("Số khách hàng đăng ký: "+listCus.size());

    }

    public ArrayList<ModelCustomer> getListNV() throws SQLException {
        ArrayList<ModelCustomer> list = new ArrayList();
        String sql = "SELECT id, hoten, sdt, email FROM tbl_khachhang;";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int id = r.getInt(1);
            String name = r.getString(2);
            String phone = r.getString(3);
            String email = r.getString(4);
            ModelCustomer data = new ModelCustomer(id, name, phone, email);
            list.add(data);
        }
        p.close();
        r.close();
        databaseConnection.releaseConnection(con);
        return list;
    }
}
